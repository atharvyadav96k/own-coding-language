package MyAsts;
import java.util.*;

public class Program extends Stmt {
    private NodeTypes type;
    private ArrayList<Stmt> body;

    public Program(ArrayList<Stmt> body){
        this.body = body;
    }

    public Program(NodeTypes type) {
        this.type = type;
    }

    @Override
    public NodeTypes getNodeTypes() {
        return this.type;
    }
}
