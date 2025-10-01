package MyAsts;
import MyTokens.*;

public class VariableDeclaration implements Stmt {
    private NodeTypes type = NodeTypes.VariableDeclaration;
    private Identifier id;
    private Stmt init;
    private TokenType idTokenType;

    public TokenType getIdType(){
        return this.idTokenType;
    }

    public void setIdType(TokenType type){
        this.idTokenType = type;
    }

    public VariableDeclaration(Identifier id, Stmt init, TokenType type) {
        this.id = id;
        this.init = init;
        this.idTokenType  = type;
    }

    @Override
    public NodeTypes getNodeType() {
        return type;
    }

    public Identifier getId() {
        return id;
    }

    public Stmt getInit() {
        return init;
    }
}
