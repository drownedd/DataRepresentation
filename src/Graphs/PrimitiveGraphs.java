package Graphs;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class PrimitiveGraphs {

    LinkedList<Vertex> vertices = new LinkedList<>();
    LinkedList<Edge> edges = new LinkedList<>();

    public void addAll(Collection<Vertex> vColl, Collection<Edge> eColl) {
        vertices.addAll(vColl);
        edges.addAll(eColl);
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public void removeVertex(Vertex v) {
        vertices.remove(v);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void addEdge(Edge e) {
        edges.add(e);
        e.vertices[0].addConnection();
        e.vertices[1].addConnection();
    }

    public void removeEdge(Edge e) {
        edges.remove(e);
        e.disconnect();
    }

    public void modifyEdge(Edge oldEdge, Vertex v1, Vertex v2) {
        oldEdge.disconnect();
        oldEdge.vertices = new Vertex[]{v1, v2};
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getOrder() {
        return vertices.size();
    }

    public abstract int getMeasure();

    private static class Vertex {
        int degree;

        public Vertex() {
            this.degree = 0;
        }

        /**
         * Called when an edge is created or modified that has this Vertex, increases degree.
         */
        public void addConnection() {
            degree += 1;
        }

        /**
         * Called when an edge is deleted or modified that had this Vertex but no longer does, decreases degree.
         */
        public void removeConnection() {
            if (degree <= 0)
                throw new NoSuchElementException();

        }
    }

    private abstract static class Edge {
        Vertex[] vertices;
        int amount;

        public Edge() {
            this.vertices = new Vertex[2];
            this.amount = 0;
        }

        public Edge(int amount) {
            this.vertices = new Vertex[2];
            this.amount = amount;
        }

        public void connect(Vertex v1, Vertex v2) {
            if (v1.equals(v2))
                throw new IllegalArgumentException("No loops allowed");
            disconnect();
            vertices[0] = v1;
            vertices[1] = v2;
            vertices[0].addConnection();
            vertices[1].addConnection();
        }

        public void disconnect() {
            vertices[0].removeConnection();
            vertices[1].removeConnection();
        }
    }

}
