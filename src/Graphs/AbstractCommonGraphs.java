package Graphs;

public abstract class AbstractCommonGraphs<E> {

    public abstract boolean addVertex(E item);

    public abstract String getVerticesString();

    public abstract String getEdgesString();

    public abstract int getOrder();

    public abstract int getMeasure();
}
