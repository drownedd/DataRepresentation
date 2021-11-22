public class ArrayPrints {

    public static void printArray(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print((arr[i][j] ? "1 " : "0 "));
            }
            System.out.println();
        }
    }
}
