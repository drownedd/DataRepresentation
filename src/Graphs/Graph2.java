package Graphs;

import java.util.ArrayList;

public class Graph2<E> {

    ArrayList<Vertex<E>> vertices;
    ArrayList<GraphEdge<E>> edges;

    public int getOrder() {
        return vertices.size();
    }

    public int getMeasure() {
        return edges.size();
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
    }
}
