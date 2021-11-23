import Graphs.DiGraph;
import Graphs.Graph;

public class Tests {

    public static void main(String[] args) {
//        Graph<Integer> g = new Graph<>();
//        g.addVertex(1);
//        g.addVertex(2);
//        g.addVertex(3);
//        g.addVertex(4);
//        g.connect(1, 2);
//        g.connect(1, 3);
//        g.connect(2, 4);
//        g.disconnect(2, 3);
//        ArrayPrints.printArray(g.adjacencyMatrix());
//        ArrayPrints.printArray(g.incidenceMatrix());
//        System.out.println("Order: \t\t" + g.getOrder());
//        System.out.println("Measure: \t" + g.getMeasure());
//        System.out.println(g.getEdgesString());
        DiGraph<Integer> dg = new DiGraph<>();
        dg.addVertex(1);
        dg.addVertex(2);
        dg.addVertex(3);
        dg.connect(1, 2);
        dg.connect(2, 3);
        dg.connect(3, 2);
        dg.connect(1, 1);
        dg.disconnect(3, 2);
        System.out.println(dg.getEdgesString());
        ArrayPrints.printArray(dg.adjacencyMatrix());
        ArrayPrints.printArray(dg.incidenceMatrix());
        System.out.println("Order: \t\t" + dg.getOrder());
        System.out.println("Measure: \t" + dg.getMeasure());
    }
}
