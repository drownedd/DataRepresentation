package Graphs;

import java.util.ArrayList;

public class DirectedGraph<E> extends AbstractPrimitiveGraph<E> {

    @Override
    public int getMeasure() {
        return 0;
    }

    @Override
    public int[][] adjMatrix() {
        return new int[0][];
    }

    @Override
    public int[][] incMatrix() {
        return new int[0][];
    }

    @Override
    public boolean addItem(E item) {
        return false;
    }

    @Override
    public ArrayList<PrimitiveEdge<E>> getEdges() {
        return null;
    }
}
