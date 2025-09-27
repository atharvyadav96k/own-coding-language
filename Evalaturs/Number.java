package Evalaturs;

public class Number implements Value{
    ValueType type = ValueType.Number;
    int value;
    @Override
    public ValueType getType(){
        return this.type;
    }
    public void setValue(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}
