package MyAsts;

public class Assignment implements Stmt {
    private NodeTypes type = NodeTypes.Assignmet;
    private Identifier id;
    private Stmt init;

    public Assignment(Identifier id, Stmt init) {
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
