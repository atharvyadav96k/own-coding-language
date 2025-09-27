package MyAsts;

public class LogicalExp implements Stmt{
    NodeTypes type = NodeTypes.Logical;
    Stmt left;
    Stmt right;
    String operator;

    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }
    public String getOperator(){
        return this.operator;
    }
    public Stmt getLeft(){
        return this.left;
    }
    public void setLeft(Stmt val){
        this.left = val;
    }
    public Stmt getRight(){
        return  this.right;
    }
    public void setRight(Stmt val){
        this.right = val;
    }
}
