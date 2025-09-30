package MyAsts;

import java.util.ArrayList;

public class Fun implements Stmt{
    NodeTypes type = NodeTypes.Fun;
    ArrayList<Stmt> body;
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }
    public void setBody(ArrayList<Stmt> body){
        this.body = body;
    }
    public ArrayList<Stmt> getBody(){
        return  this.body;
    }
}
