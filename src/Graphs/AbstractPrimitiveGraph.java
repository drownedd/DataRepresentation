package Graphs;

import java.util.ArrayList;

public abstract class AbstractPrimitiveGraph<E> implements Representable, Measurable {

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

    public abstract boolean addItem(E item);

    public abstract ArrayList<PrimitiveEdge<E>> getEdges();

    protected abstract static class PrimitiveVertex<E> {
        E item;

        protected PrimitiveVertex(E item) {
            this.item = item;
        }

        protected abstract void addConnection();

        protected abstract void removeConnection();

        public abstract int getDegree(PrimitiveVertex<E> vertex);

        public abstract boolean connect(PrimitiveVertex<E> vertex);

        public abstract boolean disconnect(PrimitiveVertex<E> vertex);
    }

    protected abstract static class PrimitiveEdge<E> {
        Object[] connections;

        PrimitiveEdge(PrimitiveVertex<E> v1, PrimitiveVertex<E> v2) {
            connections = new Object[]{v1, v2};
        }

        public abstract boolean isContained();

        public abstract void removeEdge();

        protected Object[] reverseConnections() {
            return new Object[]{connections[0], connections[1]};
        }
    }
}
