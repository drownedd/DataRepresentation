package Graphs;

public interface Vertex<E> {

    E getItem();

    boolean connectTo(Graph2<E> graph, Vertex<E> v);

    boolean disconnectFrom(Graph2<E> graph, Vertex<E> v);
}
