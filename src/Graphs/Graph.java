package Graphs;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Data structure to create, manipulate and represent graphs.
 *
 * @param <E> type of item to store in the graph's vertices.
 */
public class Graph<E> extends AbstractCommonGraphs<E> implements RepresentableGraph {

    ArrayList<Vertex<E>> vertices;
    /**
     * ArrayList where we store all the edges of the graph.
     */
    ArrayList<GraphEdge<E>> edges;

    /**
     * Constructs an empty Graph
     */
    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * Adds a vertex to the vertices list.
     *
     * @param item item to store in the vertex.
     * @return {@code true} if and only if the vertex insertion was successful,
     * {@code false} if the insertion failed due to the vertex already existing in the graph.
     */
    @Override
    public boolean addVertex(E item) {
        if (containsVertex(item)) return false;
        vertices.add(new Vertex<>(item));
        return true;
    }

    @Override
    public String getVerticesString() {
        StringBuilder result = new StringBuilder();
        for (Vertex<E> item : vertices)
            result.append("[").append(item.toString()).append("]");
        return result.toString();
    }

    @Override
    public String getEdgesString() {
        StringBuilder result = new StringBuilder();
        for (GraphEdge<E> e : edges)
            result.append("[").append(e).append("]");
        return result.toString();
    }

    @Override
    public int getOrder() {
        return vertices.size();
    }

    @Override
    public int getMeasure() {
        return edges.size();
    }

    @SuppressWarnings("ConstantConditions")
    public int getVertexDegree(E item) {
        return searchVertex(item).degree;
    }

    @Override
    public boolean[][] adjacencyMatrix() {
        var return_ = new boolean[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                return_[i][j] = containsEdge(vertices.get(i).item, vertices.get(j).item);
            }
        }
        return return_;
    }

    public boolean[][] incidenceMatrix() {
        var return_ = new boolean[vertices.size()][edges.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                return_[i][j] = edges.get(j).v1.equals(vertices.get(i)) || edges.get(j).v2.equals(vertices.get(i));
            }
        }
        return return_;
    }

    @SuppressWarnings("ConstantConditions")
    public boolean connect(E o1, E o2) {
        if (o1.equals(o2) || !containsVertex(o1) || !containsVertex(o2) || containsEdge(o1, o2)) return false;
        Vertex<E> v1 = searchVertex(o1);
        Vertex<E> v2 = searchVertex(o2);
        edges.add(new GraphEdge<>(v1, v2));
        v1.increaseDegree();
        v2.increaseDegree();
        return true;
    }

    @SuppressWarnings("ConstantConditions")
    public boolean disconnect(E o1, E o2) {
        Vertex<E> v1 = searchVertex(o1);
        Vertex<E> v2 = searchVertex(o2);
        GraphEdge<E> edge = new GraphEdge<>(v1, v2);
        if (edges.remove(edge)) {
            v1.decreaseDegree();
            v2.decreaseDegree();
            return true;
        }
        return false;
    }

    public boolean containsVertex(E o) {
        if (o == null) {
            return false;
        }
        return vertices.contains(new Vertex<>(o));
    }

    public boolean containsEdge(E e1, E e2) {
        if (e1 == null || e2 == null)
            return false;
        GraphEdge<E> edge = new GraphEdge<>(new Vertex<>(e1), new Vertex<>(e2));
        return edges.contains(edge) || edges.contains(edge.reverse());
    }

    private Vertex<E> searchVertex(E item) {
        for (Vertex<E> v : vertices) {
            if (v.equals(new Vertex<>(item)))
                return v;
        }
        return null;
    }

    /**
     * Private class that represent graph's vertices.
     */
    public static class Vertex<E> extends AbstractGraphVertex<E> {
        /**
         * Vertex degree (number of connections).
         */
        int degree;

        /**
         * Constructs a vertex with an item in it.
         *
         * @param item item to store in the vertex.
         */
        Vertex(E item) {
            super(item);
        }

        @Override
        public int getDegree() {
            return degree;
        }

        void increaseDegree() {
            degree += 1;
        }

        boolean decreaseDegree() {
            if (degree <= 0) return false;
            degree--;
            return true;
        }

        @Override
        public String toString() {
            return "(" + degree + ") " + item.toString();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex<E> v = (Vertex<E>) o;
            return Objects.equals(item, v.item);
        }
    }

    protected static class GraphEdge<E> implements Edge {
        public Vertex<E> v1;
        public Vertex<E> v2;

        protected GraphEdge(Vertex<E> v1, Vertex<E> v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public GraphEdge<E> reverse() {
            return new GraphEdge<>(v2, v1);
        }

        @Override
        public void remove() {
            v1.decreaseDegree();
            v1 = null;
            v2.decreaseDegree();
            v2 = null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || o.getClass() != getClass()) return false;

            GraphEdge<E> e = (GraphEdge<E>) o;
            return v1.equals(e.v1) && v2.equals(e.v2);
        }

        @Override
        public String toString() {
            return v1.toString() + " - " + v2.toString();
        }
    }
}
