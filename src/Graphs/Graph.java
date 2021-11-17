package Graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph<E> extends AbstractPrimitiveGraph<E> {

    ArrayList<PrimitiveEdge<E>> edges;

    public Graph() {
        this.edges = new ArrayList<>();
        this.vertices = new ArrayList<>();
    }

    @Override
    public boolean addItem(E item) {
        var vertex = new Vertex(item);
        if (vertices.contains(vertex))
            return false;
        vertices.add(new Vertex(item));
        return true;
    }

    @Override
    public int[][] adjMatrix() {
        return new int[0][];
    }

    @Override
    public int[][] incMatrix() {
        return new int[0][];
    }

    @Override
    public int getMeasure() {

        return edges.size();
    }

    @Override
    public ArrayList<PrimitiveEdge<E>> getEdges() {
        return edges;
    }

    private class Vertex extends PrimitiveVertex<E> {
        int degree;

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
            return degree == v.degree && item.equals(v.item);
        }
    }

    private class Edge extends PrimitiveEdge<E> {

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
