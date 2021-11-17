package Graphs;

public abstract class AbstractGraphVertex<E> implements GraphVertex<E> {
    E item;

    AbstractGraphVertex(E item) {
        this.item = item;
    }

    public E getItem() {
        return item;
    }
}
