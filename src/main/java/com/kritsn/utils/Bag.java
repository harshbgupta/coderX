package com.kritsn.utils;

public class Bag {

    public static int[][] create2DArray(int n, int m) {
        int[][] arr = new int[n][m];
        int counter = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = counter++;
            }
        }
//        print2DArray(arr);
        return arr;
    }

    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static String return2DArrayAsString(int[][] arr) {
        StringBuilder arrayString = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arrayString.append(arr[i][j]).append(" ");
            }
            arrayString.append("\n");
        }
        return "\n" + arrayString.toString().trim();
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "   ");
        }
        System.out.println();
    }

    /**
     * Returns a string representation of the contents of the specified array.
     *
     * @deprecated This method is a custom implementation. It is recommended to use
     *             the standard library method {@link java.util.Arrays#toString(int[])} for
     *             better performance and standardization.
     */
    @Deprecated(since = "1.1", forRemoval = true)
    public static String returnArrayAsString(int[] arr) {
        if (arr == null)
            return "null";
        int iMax = arr.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(arr[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}
