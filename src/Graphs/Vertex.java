package Graphs;

public interface Vertex<E> {

    E getItem();

    boolean connectTo(Graph<E> graph, Vertex<E> v);

    boolean disconnectFrom(Graph<E> graph, Vertex<E> v);
}
