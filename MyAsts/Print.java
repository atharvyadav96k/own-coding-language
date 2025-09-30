package MyAsts;

public class Print implements Stmt {
    NodeTypes type = NodeTypes.Print;
    Stmt print;
    public void setPrint(Stmt print){
        this.print = print;
    }
    public Stmt getPrint(){
        return this.print;
    }
    @Override
    public NodeTypes getNodeType(){
        return this.type;
    }
}
