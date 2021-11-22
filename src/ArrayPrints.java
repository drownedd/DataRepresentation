public class ArrayPrints {

    public static void printArray(boolean[][] arr) {
        for (boolean[] booleans : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print((booleans[j] ? "1 " : "0 "));
            }
            System.out.println();
        }
    }
}
