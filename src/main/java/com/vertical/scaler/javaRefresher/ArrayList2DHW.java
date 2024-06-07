package com.vertical.scaler.javaRefresher;

public class ArrayList2DHW {

    /**
     * You are given a matrix A, you have to return an array containing sum of each row elements followed by sum of
     * each column elements of the matrix.
     * NOTE: If the matrix given is of size (N x M), then the array you return would be of size (N + M), where first N
     * elements contain the sum of each N rows, and the next M elements contain the sum of each M columns.
     * TC: O(N^2)
     * SC: O(1)
     */
    private static int[] q1(int[][] A) {
        int n = A.length + A[0].length;
        int[] ans = new int[n];

        //storing sum of row
        for (int row = 0; row < A.length; row++) {
            int sum = 0;
            for (int col = 0; col < A[0].length; col++) {
                sum += A[row][col];
            }
            ans[row] = sum;
        }

        //appending and storing sum of columns
        int newIndex = A.length;
        for (int col = 0; col < A[0].length; col++) {
            int sum = 0;
            for (int row = 0; row < A.length; row++) {
                sum += A[row][col];
            }
            ans[newIndex] = sum;
            newIndex++;
        }

        return ans;
    }

    /**
     * You are given a matrix A and an integer B, you have to perform scalar multiplication of matrix A
     * with an integer B.
     * <p>
     * TC: O(N^2)
     * SC: O(1)
     */
    private static int[][] q2(int[][] A, int B) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                A[i][j] = B * A[i][j];
            }
        }
        return A;
    }

    /**
     * You are given a N X N integer matrix. You have to find the sum of all the main diagonal elements of A.
     * Main diagonal of a matrix A is a collection of elements A[i, j] such that i = j.
     * <p>
     * TC: O(N^2)
     * SC: O(1)
     */
    private static int q3(final int[][] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (i == j)
                    sum = sum + A[i][j];
            }
        }
        return sum;
    }

}
