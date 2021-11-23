public class ArrayPrints {

    public static void printArray(boolean[][] arr) {
        for (boolean[] booleans : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print((booleans[j] ? "1 " : "0 "));
            }
            System.out.println();
        }
    }

    public static void printArray(int[][] arr) {
        for (int[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                switch (ints[j]) {
                    case 0, 1 -> System.out.print(" " + ints[j] + " ");
                    case -1 -> System.out.print(ints[j] + " ");
                }
            }
            System.out.println();
        }
    }
}
