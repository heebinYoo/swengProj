package application;

import service.response.Token;

import java.sql.SQLException;

public abstract class Functionality {
    protected Token token;
    protected StateGraph stateGraph;
    private int active;
    protected int state; //cleared state, latest state, source state

    public Functionality(){
        active = 0;
    }

    /**
     all method has their own state
     object remember own state and that value is the latest executed method's state
     stateGraph.isAvailMovement(this.state, #this method's state value)
     */
    protected abstract void setUpStateGraph();

    protected void stateGraphBuilder(int nodeSize){
        stateGraph = new StateGraph(nodeSize);
    }

    protected void putEdge(int src, int dst){
        stateGraph.putEdge(0, 0);
    }

    /**
     * do this every method call
     * @param newState : method's own stage number for transaction
     */
    protected void stateChange(int newState){
        if(!this.stateGraph.isAvailMovement(this.state, newState)){
            throw new IllegalStateException();
        }
        this.state=newState;
    }




    public void startFunctionality(Token token) throws IllegalStateException, SQLException {
        if(active==0) {
            this.token = token;
            token.getConn().setAutoCommit(false);
            active = 1;
            state = -1;
        }
        else if(active==1)
            throw new IllegalStateException("is already active");
        else if(active==-1)
            throw new IllegalStateException("is dead");

    }
    public void destroyFunctionality(boolean isGood) throws SQLException {

        if(isGood)
            token.getConn().commit();
        else
            token.getConn().rollback();

        this.active = -1;
        token.getConn().setAutoCommit(true);


    }

}
