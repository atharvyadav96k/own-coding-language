package MyAsts;

import MyTokens.*;
import java.util.ArrayList;
import java.util.Stack;

public class AstTree {
    private String code;
    private ArrayList<Token> tokens;
    private int currToken = 0;

    public AstTree(String code) {
        Tokenize t = new Tokenize(code);
        this.tokens = t.run();
        // t.printTokens(this.tokens);
    }

    // Loop
    Stmt parseLoop(){
        this.currToken++;
        ArrayList<Token> tokens = ifElseConditionHelper();
        Stmt condition = this.parseExpr(tokens);
        ArrayList<Stmt> body = this.parse();
        Decision decision = new Decision(condition, body);
        Loop loop = new Loop();
        loop.setDecision(decision);
        return loop;
    }

    // if Condition helper function where it will get all condition (a < 12) like this
    ArrayList<Token> ifElseConditionHelper(){
        ArrayList<Token> conditionTokens = new ArrayList<>();
        while(this.currToken < this.tokens.size() && this.tokens.get(this.currToken).type != TokenType.OpenCurly){
            Token token = this.tokens.get(this.currToken++);
            conditionTokens.add(token);
        }
        return conditionTokens;
    }

    // if Body helper function where it will get all tokens in body and convert it into stmts

    Stmt parseSingleIfStmt(){
        this.currToken++;
        ArrayList<Token> tokens = ifElseConditionHelper();
        Stmt condition = this.parseExpr(tokens);
        ArrayList<Stmt> body = this.parse();
        Decision decision = new Decision(condition, body);
        return decision;
    }

    // if condition
    Stmt parseIfElseStmt(){
        Token cToken = this.tokens.get(this.currToken);
        ArrayList<Stmt> stmts = new ArrayList<>();
        boolean fistIf = false;
        while(cToken.type == TokenType.If || cToken.type == TokenType.Else){
            cToken = this.tokens.get(this.currToken);
            if(!fistIf && cToken.type == TokenType.If){
                fistIf = true;
                Stmt stmt = parseSingleIfStmt();
                stmts.add(stmt);
            }else if(fistIf && cToken.type == TokenType.Else){
                Token next = this.tokens.get(this.currToken + 1);
                if(next.type == TokenType.If){
                    this.currToken++;
                    Stmt stmt = parseSingleIfStmt();
                    stmts.add(stmt);
                }else{
                    Condition cond = new Condition();
                    BooleanLiteral bol = new BooleanLiteral("true");
                    cond.setLeftCondition(bol);
                    cond.setRightCondition(bol);
                    cond.setConitionType("&");
                    ArrayList<Stmt> body = this.parse();
                    Decision decision = new Decision(cond, body);
                    stmts.add(decision);
                    break;
                }
            }else{
                break;
            }
        }
        Ifelse iE = new Ifelse();
        iE.setConditions(stmts);
        return iE;
    }

    // Parse a variable declaration: let a = 12 + 2;
    public VariableDeclaration parseVarStmt() {
        Token letTok = this.tokens.get(this.currToken++);
        Token idTok = this.tokens.get(this.currToken++);
        Token eqTok = this.tokens.get(this.currToken++);
        Identifier id = new Identifier(idTok.value);

        ArrayList<Token> exprTokens = new ArrayList<>();
        while (this.currToken < this.tokens.size() && this.tokens.get(this.currToken).type != TokenType.EOS) {
            exprTokens.add(this.tokens.get(this.currToken++));
        }
        this.currToken++; // skip EOS

        Stmt init = parseExpr(exprTokens);
        VariableDeclaration varDecl = new VariableDeclaration(id, init);
        return varDecl;
    }

    // Parse an assignment: a = 12 + 2; / a = 12;
    public Assignment parAssignment() {
        Token idToken = this.tokens.get(this.currToken++);
        Token eqToken = this.tokens.get(this.currToken++);
        Identifier id = new Identifier(idToken.value);
        ArrayList<Token> exprTokens = new ArrayList<>();
        while (this.currToken < this.tokens.size() && this.tokens.get(this.currToken).type != TokenType.EOS) {
            exprTokens.add(this.tokens.get(this.currToken++));
        }
        this.currToken++;
        Stmt init = parseExpr(exprTokens);
        Assignment assignment = new Assignment(id, init);
        return assignment;
    }

    // Helper return type for parentheses extraction
    private static class ParenResult {
        ArrayList<Token> innerTokens;
        int closeIndex;
        ParenResult(ArrayList<Token> innerTokens, int closeIndex) {
            this.innerTokens = innerTokens;
            this.closeIndex = closeIndex;
        }
    }

    // Extract tokens inside parentheses, return them + index of ')'
    private ParenResult createListUntilCloseParn(ArrayList<Token> expTokens, int startIndex) {
        ArrayList<Token> exp = new ArrayList<>();
        int j = startIndex + 1;
        int depth = 1; // count nested '('

        while (j < expTokens.size() && depth > 0) {
            Token t = expTokens.get(j);
            if (t.type == TokenType.OpenParen) {
                depth++;
            } else if (t.type == TokenType.CloseParen) {
                depth--;
                if (depth == 0) break;
            }
            if (depth > 0) exp.add(t);
            j++;
        }

        if (depth != 0) {
            throw new RuntimeException("Closing ) not found");
        }

        return new ParenResult(exp, j);
    }

    // Parse simple expressions with numbers, identifiers, +, -, and ()
    public Stmt parseExpr(ArrayList<Token> expList) {
        Stack<Stmt> stack = new Stack<>();
        int i = 0;
        while (i < expList.size()) {
            Token token = expList.get(i);
            if (token.type == TokenType.Number) {
                NumericLiteral num = new NumericLiteral(Integer.parseInt(token.value));
                stack.push(num);
            }else if (token.type == TokenType.True || token.type == TokenType.False) {
                BooleanLiteral bl = new BooleanLiteral(token.value);
                stack.push(bl);
            }else if (token.type == TokenType.BinaryOperation) {
                Stmt left = stack.pop();
                i++;
                if (i >= expList.size()) throw new RuntimeException("Expected value after operator");
                Token next = expList.get(i);

                if (next.type == TokenType.Number) {
                    NumericLiteral right = new NumericLiteral(Integer.parseInt(next.value));
                    stack.push(new BinaryExpr(token.value, left, right));

                } else if (next.type == TokenType.Identifier) {
                    Identifier right = new Identifier(next.value);
                    stack.push(new BinaryExpr(token.value, left, right));

                } else if (next.type == TokenType.OpenParen) {
                    ParenResult result = createListUntilCloseParn(expList, i);
                    Stmt right = parseExpr(result.innerTokens);
                    stack.push(new BinaryExpr(token.value, left, right));
                    i = result.closeIndex; // jump to ')'
                } else {
                    throw new RuntimeException("Invalid value after operator: " + next.value);
                }

            }else if (token.type == TokenType.LogicalOperator) {
                Stmt left = stack.pop();
                i++;
                if(i >= expList.size()) throw new RuntimeException("Expected value afte operator");
                Token next = expList.get(i);
                if(next.type == TokenType.True || next.type == TokenType.False){
                    BooleanLiteral right = new BooleanLiteral(next.value);
                    Condition bEx = new Condition();
                    bEx.setConitionType(token.value);
                    bEx.setLeftCondition(left);
                    bEx.setRightCondition(right);
                    stack.push(bEx);
                }else if(next.type == TokenType.OpenParen){
                    ParenResult result = createListUntilCloseParn(expList, i);
                    Stmt right = parseExpr(result.innerTokens);
                    Condition bEx = new Condition();
                    bEx.setConitionType(token.value);
                    bEx.setLeftCondition(left);
                    bEx.setRightCondition(right);
                    stack.push(bEx);
                    i += result.closeIndex;
                }else{
                    throw new RuntimeException("Invalid Token Type for condition"+next.type);
                }
            }else if (token.type == TokenType.CompairOperator) {
                Stmt left = stack.pop();
                i++;
                if(i>= expList.size()) throw new RuntimeException("Expected tokem after operator");
                Token next = expList.get(i);
                if(next.type == TokenType.Identifier){
                    Identifier right = new Identifier(next.value);
                    Compair comp = new Compair();
                    comp.setLeft(left);
                    comp.setOperator(token.value);
                    comp.setRight(right);
                    stack.push(comp);
                }else if(next.type == TokenType.Number){
                    NumericLiteral right = new NumericLiteral(Integer.parseInt(next.value));
                    Compair comp = new Compair();
                    comp.setLeft(left);
                    comp.setOperator(token.value);
                    comp.setRight(right);
                    stack.push(comp);
                }else{
                    throw new RuntimeException("Invalid Token Type "+next.type);
                }
            }else if (token.type == TokenType.Identifier) {
                stack.push(new Identifier(token.value));
            }else if (token.type == TokenType.OpenParen) {
                ParenResult result = createListUntilCloseParn(expList, i);
                Stmt parExp = parseExpr(result.innerTokens);
                stack.push(parExp);
                i = result.closeIndex; // jump to ')'

            }else {
                throw new RuntimeException("Invalid token in expression: " + token.value);
            }
            i++;
        }

        if (stack.isEmpty()) {
            throw new RuntimeException("Empty expression");
        }
        return stack.pop();
    }

    // Main parse loop
    public ArrayList<Stmt> parse() {
        ArrayList<Stmt> body = new ArrayList<>();
        int depth = 0;
        while (this.currToken < this.tokens.size()) {
            Token token = this.tokens.get(this.currToken);
            if (token.type == TokenType.Int || token.type == TokenType.Boolean) {
                VariableDeclaration varDecl = parseVarStmt();
                body.add(varDecl);
            } else if (token.type == TokenType.Identifier) {
                Assignment assignment = parAssignment();
                body.add(assignment);
            } else if (token.type == TokenType.If) {
                body.add(this.parseIfElseStmt());
            } else if (token.type == TokenType.Loop) {
                body.add(this.parseLoop());
            }else if(token.type == TokenType.OpenCurly){
                depth++;
                this.currToken++;
            } else if (token.type == TokenType.CloseCurly) {
                this.currToken++;
                depth--;
                if(depth == 0){
                    break;
                }
            }else {
                this.currToken++;
            }
        }
        return body;
    }

    // Recursive AST printer
    private void printAST(Stmt node, int level) {
        if (node == null) return;
        for (int i = 0; i < level; i++)
            System.out.print("  ");

        if (node.getNodeType() == NodeTypes.NumericLiteral) {
            NumericLiteral num = (NumericLiteral) node;
            System.out.print(num.getValue() + " ");
        } else if (node.getNodeType() == NodeTypes.BinaryExpr) {
            BinaryExpr exp = (BinaryExpr) node;
            System.out.print(exp.getOperator());
            System.out.print(" Left: ");
            this.printAST(exp.left, 0);
            System.out.print(" Right: ");
            this.printAST(exp.right, 0);

        } else if (node.getNodeType() == NodeTypes.Identifier) {
            Identifier id = (Identifier) node;
            System.out.print(" " + id.getName() + " ");

        } else if (node.getNodeType() == NodeTypes.VariableDeclaration) {
            VariableDeclaration dec = ((VariableDeclaration) node);
            System.out.print(dec.getId().getName() + " ");
            this.printAST(dec.getInit(), 0);
        }
    }

    // Print all AST statements
    public void printAST(ArrayList<Stmt> body) {
        for (Stmt stmt : body) {
            printAST(stmt, 0);
            System.out.println();
        }
    }

    // Display tokens
    public void displayTokens() {
        for (Token token : tokens) {
            System.out.println(token.type + " : " + token.value);
        }
    }
}
