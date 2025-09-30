package MyAsts;

public class Loop implements  Stmt{
    NodeTypes type = NodeTypes.Loop;
    Stmt decision;

    public Stmt getDecision(){
        return this.decision;
    }
    public void setDecision(Stmt cond){
        this.decision = cond;
    }
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }

}
