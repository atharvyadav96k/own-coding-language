package MyAsts;

public class Condition implements Stmt{
    NodeTypes type = NodeTypes.Condition;
    Stmt left;
    Stmt right;
    String conditionType;
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }
    public void setConitionType(String conditionType){
        this.conditionType = conditionType;
    }
    public String getConditionType(){
        return  this.conditionType;
    }
    public void setLeftCondition(Stmt left){
        this.left = left;
    }
    public Stmt getLeftCondition(){
        return this.left;
    }
    public void setRightCondition(Stmt right){
        this.right = right;
    }
    public Stmt getRightCondition(){
        return this.right;
    }
}
