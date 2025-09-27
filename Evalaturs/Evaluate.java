package Evalaturs;

import MyAsts.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Evaluate {
    ArrayList<Stmt> program;
    HashMap<String, Value> mapValue = new HashMap<>();

    public Evaluate(String code) {
        AstTree tree = new AstTree(code);
        this.program = tree.parse();
        this.runCode();
    }

    boolean evaluateExpressionBoolean(Stmt stmt) {
        boolean ans = false;
        if (stmt.getNodeType() == NodeTypes.BooleanLiteral) {
            BooleanLiteral b = (BooleanLiteral) stmt;
            ans = b.value;
        } else if (stmt.getNodeType() == NodeTypes.Condition) {
            Condition cod = (Condition) stmt;
            boolean leftAns = this.evaluateExpressionBoolean(cod.getLeftCondition());
            boolean rightAns = this.evaluateExpressionBoolean(cod.getRightCondition());
            if (cod.getConditionType().equals("&")) {
                return leftAns && rightAns;
            } else if (cod.getConditionType().equals("|")) {
                return leftAns || rightAns;
            } else {
                throw new RuntimeException("Invalid Condition");
            }
        } else if (stmt.getNodeType() == NodeTypes.Identifier) {
            Identifier id = (Identifier) stmt;
            Value val = this.mapValue.get(id.getName());
            Boolean b = (Boolean) val;
            return b.getValue();
        } else {
            throw new RuntimeException("Invalid statement ");
        }
        return ans;
    }

    int evaluateExpressionInteger(Stmt stmt) {
        int ans = 0;
        if (stmt.getNodeType() == NodeTypes.NumericLiteral) {
            NumericLiteral num = (NumericLiteral) (stmt);
            ans = num.value;
        } else if (stmt.getNodeType() == NodeTypes.BinaryExpr) {
            BinaryExpr b = (BinaryExpr) stmt;
            Stmt left = b.left;
            Stmt right = b.right;
            int leftAns = this.evaluateExpressionInteger(left);
            int rightAns = this.evaluateExpressionInteger(right);
            if (b.operator.equals("+")) {
                ans = leftAns + rightAns;
            } else {
                ans = leftAns - rightAns;
            }
        } else if (stmt.getNodeType() == NodeTypes.Identifier) {
            Identifier id = (Identifier) stmt;
            Value v = (Value) mapValue.get(id.getName());
            if (v == null) {
                throw new RuntimeException(id.getName() + " : is not defined");
            } else if (v.getType() == ValueType.Null) {
                throw new RuntimeException("Can't perform operation on Null Value " + id.getName() + " = null");
            }
            if (v.getType() == ValueType.Number) {
                Number num = (Number) v;
                ans = num.value;
            }
        }
        return ans;
    }

    void evaluateStmt(Stmt stmt) {
        if (stmt.getNodeType() == NodeTypes.VariableDeclaration) {
            VariableDeclaration varDec = (VariableDeclaration) stmt;
            Identifier id = (Identifier) varDec.getId();
            Stmt init = varDec.getInit();
            if (init.getNodeType() == NodeTypes.NumericLiteral) {
                int val = this.evaluateExpressionInteger(init);
                Number num = new Number();
                num.setValue(val);
                this.mapValue.put(id.getName(), num);
                System.out.println(id.getName() + " = " + val);
            } else if (init.getNodeType() == NodeTypes.BooleanLiteral) {
                boolean val = this.evaluateExpressionBoolean(varDec.getInit());
                Boolean bol = new Boolean();
                bol.setValue(val);
                this.mapValue.put(id.getName(), bol);
                System.out.println(id.getName() + " = " + val);
            } else if (init.getNodeType() == NodeTypes.Condition) {
                Condition condition = (Condition) init;
                boolean leftAns = this.evaluateExpressionBoolean(condition.getLeftCondition());
                boolean rightAns = this.evaluateExpressionBoolean(condition.getRightCondition());
                Boolean val = new Boolean();
                if (condition.getConditionType().equals("&")) {
                    val.setValue(leftAns && rightAns);
                } else if (condition.getConditionType().equals("|")) {
                    val.setValue(leftAns || rightAns);
                } else {
                    throw new RuntimeException("Invalid operator");
                }
                System.out.println(id.getName() + " : " + val.value);
                this.mapValue.put(id.getName(), val);
            } else if (init.getNodeType() == NodeTypes.BinaryExpr) {
                int val = this.evaluateExpressionInteger(init);
                Number num = new Number();
                num.setValue(val);
                this.mapValue.put(id.getName(), num);
                System.out.println(id.getName() + " : " + val);
            } else {
                throw new RuntimeException("Invalid Type");
            }
        } else if (stmt.getNodeType() == NodeTypes.Assignmet) {
            Assignment assignment = (Assignment) stmt;
            Identifier id = (Identifier) assignment.getId();
            Stmt init = assignment.getInit();
            if (init.getNodeType() == NodeTypes.BooleanLiteral || init.getNodeType() == NodeTypes.Condition) {
                boolean val = this.evaluateExpressionBoolean(assignment.getInit());
                Boolean bol = new Boolean();
                bol.setValue(val);
                this.mapValue.put(id.getName(), bol);
                System.out.println(id.getName() + " : " + val);
            } else if (init.getNodeType() == NodeTypes.NumericLiteral || init.getNodeType() == NodeTypes.BinaryExpr) {
                int val = this.evaluateExpressionInteger(assignment.getInit());
                Number num = new Number();
                num.setValue(val);
                this.mapValue.put(id.getName(), num);
                System.out.println(id.getName() + " : " + val);
            } else if (init.getNodeType() == NodeTypes.Identifier) {
                Identifier idf = (Identifier) init;
                Value value = this.mapValue.get(idf.getName());
                if (value.getType() == ValueType.Number) {
                    int val = this.evaluateExpressionInteger(assignment.getInit());
                    Number num = new Number();
                    num.setValue(val);
                    this.mapValue.put(id.getName(), num);
                    System.out.println(id.getName() + " : " + val);
                } else if (value.getType() == ValueType.Boolean) {
                    boolean val = this.evaluateExpressionBoolean(assignment.getInit());
                    Boolean bol = new Boolean();
                    bol.setValue(val);
                    this.mapValue.put(id.getName(), bol);
                    System.out.println(id.getName() + " : " + val);
                }else{
                    throw new RuntimeException("Invalid types and perform operation on it");
                }
            }
        }
    }

    void runCode() {
        for (Stmt stmt : this.program) {
            this.evaluateStmt(stmt);
        }
        System.out.println(this.mapValue.size());
    }
}