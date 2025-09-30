package MyAsts;

public class StringLiteral implements Stmt{
    NodeTypes type = NodeTypes.String;
    String value;
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }
}
