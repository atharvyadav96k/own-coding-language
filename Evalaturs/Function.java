package Evalaturs;

import MyAsts.*;
import java.util.ArrayList;

public class Function implements  Value{
    ValueType type = ValueType.Function;
    ArrayList<Stmt> body ;
    @Override
    public ValueType getType(){
        return this.type;
    }
    public ArrayList<Stmt> getBody(){
        return this.body;
    }
    public void setBody(ArrayList<Stmt> body){
        this.body = body;
    }
}
