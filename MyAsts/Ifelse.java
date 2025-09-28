package MyAsts;

import java.util.ArrayList;

public class Ifelse implements Stmt {
    NodeTypes type = NodeTypes.Ifelse;
    ArrayList<Decision> condition = new ArrayList<>();
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }
    public ArrayList<Decision> getConditions(){
        return this.condition;
    }
    public void setConditions(ArrayList<Decision> conditions){
        this.condition = conditions;
    }
}
