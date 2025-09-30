package MyAsts;

public class CallFun implements Stmt{
    NodeTypes type = NodeTypes.Call;
    String name;
    public void setCallName(String name){
        this.name = name;
    }
    public String getFunName(){
        return this.name;
    }
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }

}
