package MyAsts;

public class VariableDeclaration implements Stmt {
    private NodeTypes type = NodeTypes.VariableDeclaration;
    private Identifier id;
    private Stmt init;

    public VariableDeclaration(Identifier id, Stmt init) {
        this.id = id;
        this.init = init;
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
