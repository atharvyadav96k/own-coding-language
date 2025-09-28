package MyAsts;
import java.util.ArrayList;

public class Decision implements Stmt {
    NodeTypes type = NodeTypes.Decision;
    Stmt cond;
    ArrayList<Stmt> program;
    public Decision(Stmt cond, ArrayList<Stmt> program){
        this.cond = cond;
        this.program = program;
    }
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }
    public ArrayList<Stmt> getProgram(){
        return  this.program;
    }
    public Stmt getCondition(){
        return  this.cond;
    }
}
