import Graphs.Graph;

public class Main {

    public static void main(String[] args) {
        var graph = new Graph<Integer>();
        graph.addItem(1);
        graph.addItem(1);
        graph.addItem(2);
        System.out.print(graph.getVertices());

        int a = graph.getMeasure();
        System.out.println(a);
        int b = graph.getOrder();
        System.out.println(b);

        var c = graph.getVertex(1);
    }
}
