package MyAsts;

import java.util.ArrayList;

public class Fun implements Stmt{
    NodeTypes type = NodeTypes.Fun;
    ArrayList<Stmt> body;
    String funName;
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }
    public String getFunName(){
        return this.funName;
    }
    public void setFunName(String name){
        this.funName = name;
    }
    public void setBody(ArrayList<Stmt> body){
        this.body = body;
    }
    public ArrayList<Stmt> getBody(){
        return  this.body;
    }
}
