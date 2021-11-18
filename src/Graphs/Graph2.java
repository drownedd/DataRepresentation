package Graphs;

import java.util.ArrayList;
import java.util.Objects;

public class Graph2<E> extends AbstractCommonGraphs implements RepresentableGraph {

    ArrayList<Vertex<E>> vertices;
    ArrayList<GraphEdge<E>> edges;

    @Override
    public int getOrder() {
        return vertices.size();
    }

    @Override
    public int getMeasure() {
        return edges.size();
    }

    @Override
    public boolean[][] adjacentMatrix() {
        return new boolean[0][];
    }

    @Override
    public boolean[][] incidentMatrix() {
        return new boolean[0][];
    }

    @SuppressWarnings("unchecked")
    public boolean containsVertex(Object v) {
        if (v == null || v.getClass() != Vertex.class) return false;
        return vertices.contains((Vertex<E>) v);
    }

    @SuppressWarnings("unchecked")
    public boolean containsEdge(Object e) {
        if (e == null || e.getClass() != GraphEdge.class) return false;
        GraphEdge<E> edge = (GraphEdge<E>) e;
        return edges.contains(edge) || edges.contains(edge.reverse());
    }

    public static class Vertex<E> extends AbstractGraphVertex<E> {
        int degree;

        Vertex(E item) {
            super(item);
        }

        @Override
        public int getDegree() {
            return degree;
        }

        @Override
        public void increaseDegree() {
            degree += 1;
        }

        @Override
        public boolean decreaseDegree() {
            if (degree <= 0) return false;
            degree--;
            return true;
        }

        @Override
        public boolean connectTo(Graph2<E> graph, Graphs.Vertex<E> v) {
            GraphEdge<E> edge = new GraphEdge<E>(this, (Vertex<E>) v);
            if (graph.containsEdge(edge) || !graph.containsVertex(v))
                return false;
            graph.edges.add(edge);
            return true;
        }

        @Override
        public boolean disconnectFrom(Graph2<E> graph, Graphs.Vertex<E> v) {
            GraphEdge<E> edge = new GraphEdge<E>(this, (Vertex<E>) v);
            return graph.edges.remove(edge);
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
        protected Vertex<E> v1;
        protected Vertex<E> v2;

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

        public boolean isContainedIn(Graph2<E> graph) {
            return graph.containsVertex(this) ||
        }
    }
}
