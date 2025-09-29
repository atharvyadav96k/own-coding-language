package MyAsts;

import java.util.ArrayList;

public class Ifelse implements Stmt {
    NodeTypes type = NodeTypes.Ifelse;
    ArrayList<Stmt> condition = new ArrayList<>();
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }
    public ArrayList<Stmt> getConditions(){
        return this.condition;
    }
    public void setConditions(ArrayList<Stmt> conditions){
        this.condition = conditions;
    }
}
