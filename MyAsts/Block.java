package MyAsts;

import java.util.ArrayList;

public class Block implements Stmt {
    NodeTypes type = NodeTypes.Block;
    ArrayList<Stmt> statements;

    public Block(ArrayList<Stmt> statements) {
        this.statements = statements;
    }

    @Override
    public NodeTypes getNodeType() {
        return this.type;
    }

    public ArrayList<Stmt> getStatements() {
        return this.statements;
    }
}
