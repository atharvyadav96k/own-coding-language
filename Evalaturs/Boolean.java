package Evalaturs;

public class Boolean implements Value {
    ValueType type = ValueType.Boolean;
    boolean value = false;
    @Override
    public ValueType getType(){
        return this.type;
    }
    public void setValue(boolean value){
        this.value = value;
    }
    public boolean getValue(){
        return this.value;
    }
}
