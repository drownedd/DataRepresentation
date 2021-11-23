package Graphs;

import java.util.ArrayList;
import java.util.Objects;

public class DiGraph<E> extends AbstractCommonGraphs<E> implements RepresentableGraph {

    ArrayList<Vertex<E>> vertices;
    ArrayList<dEdge<E>> edges;

    public DiGraph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public boolean addVertex(E item) {
        if (containsVertex(item)) return false;
        vertices.add(new Vertex<>(item));
        return true;
    }

    @SuppressWarnings("ConstantConditions")
    public int getVertexInDegree(E item) {
        return searchVertex(item).inDegree;
    }

    @SuppressWarnings("ConstantConditions")
    public int getVertexOutDegree(E item) {
        return searchVertex(item).outDegree;
    }

    public boolean containsVertex(E o) {
        if (o == null) {
            return false;
        }
        Vertex<E> v = new Vertex<>(o);
        return vertices.contains(v);
    }

    public boolean containsEdge(E o1, E o2) {
        if (o1 == null || o2 == null)
            return false;
        dEdge<E> edge = new dEdge<>(new Vertex<>(o1), new Vertex<>(o2));
        return edges.contains(edge);
    }

    @SuppressWarnings("ConstantConditions")
    public boolean connect(E o1, E o2) {
        if (o1.equals(o2) || !containsVertex(o1) || !containsVertex(o2) || containsEdge(o1, o2)) return false;
        Vertex<E> v1 = searchVertex(o1);
        Vertex<E> v2 = searchVertex(o2);
        edges.add(new dEdge<>(v1, v2));
        v1.increaseOutDegree();
        v2.increaseInDegree();
        return true;
    }

    @SuppressWarnings("ConstantConditions")
    public boolean disconnect(E o1, E o2) {
        Vertex<E> v1 = searchVertex(o1);
        Vertex<E> v2 = searchVertex(o2);
        dEdge<E> edge = new dEdge<>(v1, v2);
        if (edges.remove(edge)) {
            v1.decreaseOutDegree();
            v2.decreaseInDegree();
            return true;
        }
        return false;
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
        for (dEdge<E> e : edges)
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

    public int[][] incidenceMatrix() {
        var return_ = new int[vertices.size()][edges.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                dEdge<E> currEdge = edges.get(j);
                if (currEdge.v1.equals(vertices.get(i)))
                    return_[i][j] = -1;
                else if (currEdge.v2.equals(vertices.get(i)))
                    return_[i][j] = 1;
            }
        }
        return return_;
    }

    private Vertex<E> searchVertex(E item) {
        for (Vertex<E> v : vertices) {
            if (v.equals(new Vertex<>(item)))
                return v;
        }
        return null;
    }

    /**
     * Private class that represent digraph's vertices.
     */
    public static class Vertex<E> extends AbstractDiGraphVertex<E> {
        /**
         * Vertex incoming degree (number of incoming connections).
         */
        int inDegree;
        /**
         * Vertex outgoing degree (number of outgoing connections).
         */
        int outDegree;

        /**
         * Constructs a vertex with an item in it.
         *
         * @param item item to store in the vertex.
         */
        Vertex(E item) {
            super(item);
        }

        @Override
        public int getInDegree() {
            return inDegree;
        }

        @Override
        public int getOutDegree() {
            return outDegree;
        }

        void increaseInDegree() {
            inDegree += 1;
        }

        boolean decreaseInDegree() {
            if (inDegree <= 0) return false;
            inDegree -= 1;
            return true;
        }

        void increaseOutDegree() {
            outDegree += 1;
        }

        boolean decreaseOutDegree() {
            if (outDegree <= 0) return false;
            outDegree -= 1;
            return true;
        }

        @Override
        public String toString() {
            return "(" + inDegree + ", " + outDegree + ") " + item.toString();
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

    protected static class dEdge<E> implements Edge {
        public Vertex<E> v1;
        public Vertex<E> v2;

        protected dEdge(Vertex<E> v1, Vertex<E> v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public dEdge<E> reverse() {
            return new dEdge<>(v2, v1);
        }

        @Override
        public void remove() {
            v1.decreaseOutDegree();
            v1 = null;
            v2.decreaseInDegree();
            v2 = null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || o.getClass() != getClass()) return false;

            dEdge<E> e = (dEdge<E>) o;
            return v1.equals(e.v1) && v2.equals(e.v2);
        }

        @Override
        public String toString() {
            return v1.toString() + " -> " + v2.toString();
        }
    }
}
