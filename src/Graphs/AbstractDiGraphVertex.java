package Graphs;

public abstract class AbstractDiGraphVertex<E> implements DiGraphVertex {

    E item;

    AbstractDiGraphVertex(E item) {
        this.item = item;
    }

    public E getItem() {
        return item;
    }
}
