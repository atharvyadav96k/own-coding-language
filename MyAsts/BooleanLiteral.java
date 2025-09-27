package MyAsts;

public class BooleanLiteral implements Stmt{
    private NodeTypes type = NodeTypes.BooleanLiteral;
    public boolean  value;

    public BooleanLiteral(String value) {
        if(value.equals("true")){
            this.value = true;
        }else if(value.equals("false")){
            this.value = false;
        }else {
            throw new RuntimeException("Not a valid type : "+value);
        }
    }

    @Override
    public NodeTypes getNodeType() {
        return type;
    }

    public boolean  getValue() {
        return this.value;
    }
}
