package com.vertical.scaler.javaRefresher;

public class Array2DHW {

    /**
     * You are given two matrices A & B of equal dimensions and you have to check whether two matrices are equal or not.
     * <p>
     * NOTE: Both matrices are equal if A[i][j] == B[i][j] for all i and j in the given range.
     * TC: O(N)
     * SC: O(1)
     */
    private static int q1(int[][] A, int[][] B) {
        if (A.length != B.length) return 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != B[i][j])
                    return 0;
            }
        }
        return 1;
    }

    /**
     * You are given two integer matrices A and B having same size(Both having same number of rows (N) and columns (M).
     * You have to subtract matrix A from B and return the resultant matrix. (i.e. return the matrix A - B).
     * <p>
     * If X and Y are two matrices of the same order (same dimensions). Then X - Y is a matrix of the same order as X
     * and Y and its elements are obtained by doing an element wise subtraction of X from Y.
     * <p>
     * TC: O(N^2)
     * SC: O(N^2)
     */
    private static int[][] q2(int[][] A, int[][] B) {
        int[][] ans = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                ans[i][j] = (A[i][j] - B[i][j]);
            }
        }
        return ans;
    }

    /**
     * Given a 2D integer array C[][] of A rows and B columns. Return an integer array of size B that represents the
     * sums of the columns of the 2D array C.
     * <p>
     * TC: O(N^2)
     * SC: O(N)
     */
    private static int[] q3(int A, int B, int[][] C) {
        int[] ans = new int[B];
        for (int i = 0; i < B; i++) {
            int sum = 0;
            for (int j = 0; j < A; j++) {
                sum += C[j][i];
            }
            ans[i] = sum;
        }
        return ans;
    }

    /**
     * Given a 2D array A of N rows and M columns. Find value of largest element in each row.
     * <p>
     * TC: O(N^2)
     * SC: O(N)
     */
    private static int[] solve(int[][] A) {
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int max = 0;
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] > max)
                    max = A[i][j];
            }
            ans[i] = max;
        }
        return ans;
    }
}
