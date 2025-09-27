package Evalaturs;

public class Null implements Value{
    ValueType type = ValueType.Null;
    @Override
    public ValueType getType(){
        return this.type;
    }
}
