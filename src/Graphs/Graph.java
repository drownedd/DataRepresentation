package Graphs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Data structure to create, manipulate and represent graphs.
 *
 * @param <E> type of item to store in the graph's vertices.
 */
public class Graph<E> extends AbstractPrimitiveGraph<E> {

    /**
     * ArrayList where we store all the edges of the graph.
     */
    ArrayList<PrimitiveEdge<E>> edges;

    /**
     * Constructs an empty Graph
     */
    public Graph() {
        this.edges = new ArrayList<>();
        this.vertices = new ArrayList<>();
    }

    @Override
    public boolean addItem(E item) {
        var vertex = new Vertex(item);
        if (vertices.contains(vertex)) return false;
        vertices.add(new Vertex(item));
        return true;
    }

    @Override
    public PrimitiveVertex<E> getVertex(E item) {
        for (PrimitiveVertex<E> v : vertices) {
            if (vertices.contains(v))
                return v;
        }
        return null;
    }

    @Override
    public int[][] adjMatrix() {
        return new int[0][];
    }

    @Override
    public int[][] incMatrix() {
        return new int[0][];
    }

    public int getMeasure() {
        return edges.size();
    }

    @Override
    public ArrayList<PrimitiveEdge<E>> getEdges() {
        return edges;
    }

    /**
     * Private class that represent graph's vertices.
     */
    private class Vertex extends PrimitiveVertex<E> {
        /**
         * Vertex degree (number of connections).
         */
        int degree;

        /**
         * Constructs a vertex with an item in it.
         *
         * @param item item to store in the vertex.
         */
        public Vertex(E item) {
            super(item);
        }

        @Override
        public void addConnection() {
            degree += 1;
        }

        @Override
        public void removeConnection() {
            if (degree <= 0)
                throw new IllegalStateException("Vertex didn't have any connection.");
            degree -= 1;
        }

        @Override
        public int getDegree(PrimitiveVertex<E> vertex) {
            return degree;
        }

        @Override
        public boolean connect(PrimitiveVertex<E> vertex) {
            Edge edge = new Edge(this, vertex);
            if (!vertices.contains(vertex) || edge.isContained())
                return false;
            vertex.addConnection();
            addConnection();
            edges.add(edge);
            return true;
        }

        @Override
        public boolean disconnect(PrimitiveVertex<E> vertex) {
            Edge edge = new Edge(this, vertex);
            if (!(edges.contains(edge) || edges.contains(edge.reverse()))) return false;
            vertices.remove(vertex);
            if (!edges.remove(edge))
                edges.remove(edge.reverse());
            return true;
        }

        @Override
        public String toString() {
            return "(" + degree + ")" + item.toString();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex v = (Vertex) o;
            return item.equals(v.item);
        }
    }

    /**
     * Private class that represents graph's edges.
     */
    private class Edge extends PrimitiveEdge<E> {

        /**
         * Constructs an edge.
         *
         * @param v1 one of the vertex it's connected to.
         * @param v2 the other vertex it's connected to.
         */
        Edge(PrimitiveVertex<E> v1, PrimitiveVertex<E> v2) {
            super(v1, v2);
        }

        @SuppressWarnings("unchecked")
        private Edge reverse() {
            return new Edge((Vertex) connections[1], (Vertex) connections[0]);
        }

        @Override
        public boolean isContained() {
            return edges.contains(this) || edges.contains(reverse());
        }

        @Override
        @SuppressWarnings("unchecked")
        public void removeEdge() {
            for (Object o : connections) {
                var v = (PrimitiveVertex<E>) o;
                v.removeConnection();
            }
            edges.remove(this);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            return Arrays.equals(this.connections, edge.connections) ||
                    Arrays.equals(reverseConnections(), edge.connections);
        }
    }
}
