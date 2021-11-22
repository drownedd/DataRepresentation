import Graphs.Graph;

public class Tests {

    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.connect(1, 2);
        g.connect(1, 3);
        g.connect(2, 4);
        g.disconnect(2, 3);
        ArrayPrints.printArray(g.adjacenceMatrix());
        ArrayPrints.printArray(g.incidenceMatrix());
        System.out.println("Order: \t\t" + g.getOrder());
        System.out.println("Measure: \t" + g.getMeasure());
    }
}
