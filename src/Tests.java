import Graphs.Graph;

public class Tests {

    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();
        g.addItem(1);
        g.addItem(2);
        var v1 = g.getVertex(1);
        var v2 = g.getVertex(2);
        v1.connectTo(g, v2);
        System.out.println(g.getEdgesString());
        boolean[][] adjMat = g.adjacenceMatrix();
        for (int i = 0; i < adjMat.length; i++) {
            for (int j = 0; j < adjMat[0].length; j++) {
                System.out.print(adjMat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
