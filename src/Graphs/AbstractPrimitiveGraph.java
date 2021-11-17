package Graphs;

import java.util.ArrayList;

public abstract class AbstractPrimitiveGraph<E> implements Representable {

    ArrayList<PrimitiveVertex<E>> vertices;

    public ArrayList<PrimitiveVertex<E>> getVertices() {
        return vertices;
    }

    public int getOrder() {
        return vertices.size();
    }

    public E getItem(PrimitiveVertex<? extends E> v) {
        return v.item;
    }

    /**
     * Adds a vertex to the vertices list.
     *
     * @param item item to store in the vertex.
     * @return {@code true} if and only if the vertex insertion was successful,
     * {@code false} if the insertion failed due to the vertex already existing in the graph.
     */
    public abstract boolean addItem(E item);

    /**
     * Get the graph's list of edges.
     *
     * @return the list of edges.
     */
    public abstract ArrayList<PrimitiveEdge<E>> getEdges();

    /**
     * Returns the vertex that contains the given item.
     *
     * @param item item to look for.
     * @return the vertex that contains the given item.
     */
    public abstract PrimitiveVertex<E> getVertex(E item);

    /**
     * Primitive vertex representation which contains all the properties
     * shared among every kind of graph vertex.
     *
     * @param <E> type of item to store in the vertex.
     */
    protected abstract static class PrimitiveVertex<E> {
        /**
         * Stored item.
         */
        E item;

        /**
         * Constructor for subclasses to inherit.
         *
         * @param item item to store in the vertex.
         */
        protected PrimitiveVertex(E item) {
            this.item = item;
        }

        /**
         * An edge connected this vertex with another, therefore its degree increases.
         */
        protected abstract void addConnection();

        /**
         * An edge that connected this vertex with another was removed,
         * therefore its degree is decreased.
         *
         * @throws IllegalStateException if the vertex didn't have any connection.
         */
        protected abstract void removeConnection();

        public abstract int getDegree(PrimitiveVertex<E> vertex);

        /**
         * Create a new edge joining this vertex to the given one.
         *
         * @param vertex given vertex to connect this one with.
         * @return {@code true} iif the connection succeeded,
         * {@code false} if the connection already existed and therefore wasn't created.
         */
        public abstract boolean connect(PrimitiveVertex<E> vertex);

        /**
         * Removes the edge that joined this vertex to the given one.
         *
         * @param vertex vertex this one was connected with.
         * @return {@code true} iif the disconnection succeeded and therefore the edge
         * already existed and was removed, {@code false} otherwise.
         */
        public abstract boolean disconnect(PrimitiveVertex<E> vertex);
    }

    /**
     * Private class that represents graph's edges.
     *
     * @param <E> types of the stored items in the vertex joined by the edge.
     */
    protected abstract static class PrimitiveEdge<E> {
        /**
         * Array size 2 where to store a reference to both vertices.
         */
        Object[] connections;

        /**
         * Constructs for subclasses to inherit.
         *
         * @param v1 one of the vertex.
         * @param v2 the other vertex.
         */
        PrimitiveEdge(PrimitiveVertex<E> v1, PrimitiveVertex<E> v2) {
            connections = new Object[]{v1, v2};
        }

        /**
         * Checks if this edge is contained in the graph.
         *
         * @return {@code true} iif this edge is contained in the graph,
         * {@code false} otherwise.
         */
        public abstract boolean isContained();

        /**
         * Removes this edge from the graph, updating its vertices degree.
         */
        public abstract void removeEdge();

        /**
         * Gives the edge turned, useful for non-directed graphs.
         *
         * @return the edge turned.
         */
        protected Object[] reverseConnections() {
            return new Object[]{connections[0], connections[1]};
        }
    }
}
