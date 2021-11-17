package Graphs;

public interface GraphVertex<E> extends Vertex<E> {

    int getDegree();

    void increaseDegree();

    boolean decreaseDegree();
}
