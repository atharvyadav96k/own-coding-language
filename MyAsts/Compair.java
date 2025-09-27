package MyAsts;

public class Compair implements Stmt{
    NodeTypes type = NodeTypes.Compair;
    String operator;
    Stmt left;
    Stmt right;
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }
    public void setOperator(String str){
        this.operator = str;
    }
    public String getOperator(){
        return this.operator;
    }
    public Stmt getLeft(){
        return this.left;
    }
    public void setLeft(Stmt left){
        this.left = left;
    }
    public Stmt getRight(){
        return this.right;
    }
    public void setRight(Stmt right){
        this.right = right;
    }
}
