package MyAsts;

public class BinaryExpr implements Stmt {
    private NodeTypes type = NodeTypes.BinaryExpr;
    public String operator;
    public Stmt left;
    public Stmt right;

    public BinaryExpr(String operator, Stmt left, Stmt right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public NodeTypes getNodeType() {
        return type;
    }

    public String getOperator() {
        return operator;
    }

    public Stmt getLeft() {
        return left;
    }

    public Stmt getRight() {
        return right;
    }
}
