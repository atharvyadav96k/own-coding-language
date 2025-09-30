package MyTokens;
import java.util.ArrayList;
import java.util.HashMap;


public class Tokenize {
    private final String sourceCode;
    private final HashMap<String, TokenType> keyWords = new HashMap<>();

    public Tokenize(String sourcecode) {
        this.sourceCode = sourcecode;
        this.reservedTokens();
    }

    void reservedTokens() {
        keyWords.put("int", TokenType.Int); 
        keyWords.put("bool", TokenType.Boolean);
        keyWords.put("true", TokenType.True);
        keyWords.put("false", TokenType.False);
        keyWords.put("if", TokenType.If);
        keyWords.put("else", TokenType.Else);
        keyWords.put("while", TokenType.Loop);
        keyWords.put("fun", TokenType.Fun);
        keyWords.put("call", TokenType.Call);
        keyWords.put("print", TokenType.Print);
    }

    boolean bitWise(char c){
        return c == '&' || c == '|' ? true : false;
    }
    Token token(String value, TokenType type) {
        return new Token(value, type);
    }

    boolean isSkippable(char ch) {
        return Character.isWhitespace(ch);
    }

    public void printTokens(ArrayList<Token> tokens){
        for(Token token : tokens){
            System.out.println(token.value +" : "+token.type);
        }
    }

    public ArrayList<Token> run() {
        ArrayList<Token> tokens = new ArrayList<>();
        int i = 0;

        while (i < sourceCode.length()) {
            char ch = sourceCode.charAt(i);
            // Single-character tokens
            if (ch == '(') {
                tokens.add(this.token("(", TokenType.OpenParen));
                i++;
            } else if (ch == ')') {
                tokens.add(this.token(")", TokenType.CloseParen));
                i++;
            } else if ("+-*/".indexOf(ch) != -1) {
                tokens.add(this.token(String.valueOf(ch), TokenType.BinaryOperation));
                i++;
            } else if (ch == '=') {
                tokens.add(this.token("=", TokenType.Equals));
                i++;
            }else if(ch == ';'){
                tokens.add(this.token(";", TokenType.EOS));
                i++;
            } else if (ch == '{') {
                tokens.add(this.token("{", TokenType.OpenCurly));
                i++;
            }else if (ch == '}') {
                tokens.add(this.token("}", TokenType.CloseCurly));
                i++;
            }else if (ch == '&'  || ch == '|') {
                tokens.add(this.token(""+ch, TokenType.LogicalOperator));
                i++;
            }else if (ch == '<' || ch == '>') {
                tokens.add(this.token(""+ch, TokenType.CompairOperator));
                i++;
            } else if (Character.isDigit(ch)) {
                // Numbers
                StringBuilder numBuffer = new StringBuilder();
                while (i < sourceCode.length() && Character.isDigit(sourceCode.charAt(i))) {
                    numBuffer.append(sourceCode.charAt(i));
                    i++;
                }
                tokens.add(this.token(numBuffer.toString(), TokenType.Number));

            // Identifiers / Keywords
            } else if (Character.isAlphabetic(ch)) {
                StringBuilder idBuffer = new StringBuilder();
                while (i < sourceCode.length() && Character.isAlphabetic(sourceCode.charAt(i))) {
                    idBuffer.append(sourceCode.charAt(i));
                    i++;
                }
                String word = idBuffer.toString();
                TokenType reserved = this.keyWords.get(word);
                if (reserved != null) {
                    tokens.add(this.token(word, reserved));
                } else {
                    tokens.add(this.token(word, TokenType.Identifier));
                }

            // Whitespace → skip
            } else if (isSkippable(ch)) {
                i++;
            // Unknown token
            } else {
                throw new RuntimeException("Unrecognized token: " + ch);
            }
        }

        tokens.add(this.token("EndOfFile", TokenType.EOF));
        return tokens;
    }
}
