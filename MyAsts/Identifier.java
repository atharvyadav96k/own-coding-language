package MyAsts;

public class Identifier implements Stmt {
    private NodeTypes type = NodeTypes.Identifier;
    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public NodeTypes getNodeType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
