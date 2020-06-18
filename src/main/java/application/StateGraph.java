package application;

import java.util.ArrayList;
import java.util.HashMap;

public class StateGraph {

    // key : node number
    // value : the edge can go from key(node)
    private HashMap<Integer, ArrayList<Integer>> listGraph;

    public StateGraph(int nodeSize){

        listGraph = new HashMap<>();

        for (int i=0; i<nodeSize+1; i++){
            listGraph.put(i, new ArrayList<>());
        }

        this.putEdge(-1, 0);

    }

    public void putEdge(int src, int dst) {
        listGraph.get(src).add(dst);
    }

    public boolean isAvailMovement(int src, int dst){
        return listGraph.get(src).contains(dst);

    }

}
