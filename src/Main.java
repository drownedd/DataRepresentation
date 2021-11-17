import Graphs.Graph;

public class Main {

    public static void main(String[] args) {
        var graph = new Graph<Integer>();
        graph.addItem(1);
        graph.addItem(1);
        graph.addItem(2);
        System.out.print(graph.getVertices());
    }
}
