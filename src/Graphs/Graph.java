package Graphs;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Data structure to create, manipulate and represent graphs.
 *
 * @param <E> type of item to store in the graph's vertices.
 */
public class Graph<E> extends AbstractCommonGraphs implements RepresentableGraph {

    /**
     * Constructs an empty Graph
     */
    ArrayList<Vertex<E>> vertices;
    /**
     * ArrayList where we store all the edges of the graph.
     */
    ArrayList<GraphEdge<E>> edges;

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
    public boolean addVertex(E item) {
        Vertex<E> v = new Vertex<>(item);
        if (this.containsVertex(v)) return false;
        vertices.add(v);
        return true;
    }

    @Override
    public String getVerticesString() {
        var result = "";
        for (Vertex<E> item : vertices)
            result += "[" + item.toString() + "]";
        return result;
    }

    @Override
    public String getEdgesString() {
        var result = "";
        for (GraphEdge<E> e : edges)
            result += "[" + e + "]";
        return result;
    }

    @Override
    public int getOrder() {
        return vertices.size();
    }

    @Override
    public int getMeasure() {
        return edges.size();
    }

    @Override
    public boolean[][] adjacencyMatrix() {
        var return_ = new boolean[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                return_[i][j] = containsEdge(new GraphEdge<>(vertices.get(i), vertices.get(j)));
            }
        }
        return return_;
    }

    @Override
    public boolean[][] incidenceMatrix() {
        var return_ = new boolean[vertices.size()][edges.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                return_[i][j] = edges.get(j).v1.equals(vertices.get(i)) || edges.get(j).v2.equals(vertices.get(i));
            }
        }
        return return_;
    }

    public boolean connect(E o1, E o2) {
        Vertex<E> v1 = new Vertex<>(o1);
        Vertex<E> v2 = new Vertex<>(o2);
        GraphEdge<E> edge = new GraphEdge<>(v1, v2);
        if (!containsVertex(v1) || !containsVertex(v2) || containsEdge(edge)) return false;
        edges.add(edge);
        v1.increaseDegree();
        v2.increaseDegree();
        return true;
    }

    public boolean disconnect(E o1, E o2) {
        Vertex<E> v1 = new Vertex<>(o1);
        Vertex<E> v2 = new Vertex<>(o2);
        GraphEdge<E> edge = new GraphEdge<>(v1, v2);
        boolean hasRemoved = edges.remove(edge);
        if (hasRemoved) {
            v1.decreaseDegree();
            v2.decreaseDegree();
        }
        return hasRemoved;
    }

    @SuppressWarnings("unchecked")
    public boolean containsVertex(Object o) {
        if (o == null || !(o.getClass() == Vertex.class || o.getClass() == vertices.get(0).item.getClass())) {
            return false;
        }
        Vertex<E> v;
        if (o.getClass() == Vertex.class) {
            v = (Vertex<E>) o;
        } else
            v = new Vertex<>((E) o);
        return vertices.contains(v);
    }

    @SuppressWarnings("unchecked")
    public boolean containsEdge(Object e) {
        if (e == null || e.getClass() != GraphEdge.class) return false;
        GraphEdge<E> edge = (GraphEdge<E>) e;
        return edges.contains(edge) || edges.contains(edge.reverse());
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
            return "(" + degree + ")" + item.toString();
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
