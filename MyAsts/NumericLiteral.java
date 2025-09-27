package MyAsts;

public class NumericLiteral implements Stmt {
    private NodeTypes type = NodeTypes.NumericLiteral;
    public int value;

    public NumericLiteral(int value) {
        this.value = value;
    }

    @Override
    public NodeTypes getNodeType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
