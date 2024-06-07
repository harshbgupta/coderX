package com.vertical.scaler.dsa.intrermediate1;

public class Matrix2dHW {
    /**
     * You are given a matrix A and and an integer B, you have to perform scalar multiplication of matrix A with an
     * integer B
     * <p>
     * TC:O(N^2)
     * SC: O(N^2) as of now, but if change given matrix and replace the same value after multiplication then
     * SC: O(1)
     */
    public static int[][] solve(int[][] A, int B) {
        int[][] ans = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                ans[i][j] = B * A[i][j];
            }
        }
        return ans;
    }

    /**
     * You are given two matrices A & B of equal dimensions and you have to check whether two matrices are equal or not.
     * <p>
     * NOTE: Both matrices are equal if A[i][j] == B[i][j] for all i and j in the given range.
     * <p>
     * TC: O(N^2)
     * SC: O(1)
     */
    private static int q2(int[][] A, int[][] B) {
        boolean flag = true;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != B[i][j]) {
                    return 0;
                }
            }
        }
        return 1;
    }

    /**
     * You are given two matrices A & B of same size, you have to return another matrix which is the sum of A and B.
     * <p>
     * TC:O(N^2)
     * SC:O(N^2)
     */
    private static int[][] q3(int[][] A, int[][] B) {
        int[][] ans = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                ans[i][j] = A[i][j] + B[i][j];
            }
        }
        return ans;
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
    private static int[][] q4(int[][] A, int[][] B) {
        int[][] ans = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                ans[i][j] = A[i][j] - B[i][j];
            }
        }
        return ans;
    }

    /**
     * TC:
     * SC:
     */
    private static int[][] solve(int[][] A) {
        int[][] ans = new int [A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] ==0){
                    ans = makeElementZero(A,i,j);
                }
            }
        }
        return ans;
    }

    /**
     * TC:
     * SC:
     */
    private static int[][] makeElementZero(int[][] arr, int i, int j) {
        int[][] ans = new int [arr.length][arr[0].length];
        //making a whole row 0
        for (int col = 0; col < arr[0].length; col++) {
            ans[i][col] = 0;
        }

        //making a whole column 0
        for (int row = 0; row < arr.length; row++) {
            ans[row][j] = 0;
        }
        return ans;
    }

    /**
     *You are given a 2D integer matrix A, make all the elements in a row or column zero if the A[i][j] = 0.
     * Specifically, make entire ith row and jth column zero.
     *
     * TC: O(N^2)
     * SC:
     */
    private static int[][] q7HintSolution(int[][] A){
        int n = A.length, m = A[0].length;
        //making all row -1 instead of zero
        for(int i = 0; i < n; i++){
            int flag = 0;
            for(int j = 0; j < m; j++){
                if(A[i][j]==0)flag = 1;
            }
            if(flag == 1){
                for(int j = 0; j < m; j++){
                    if(A[i][j] != 0) A[i][j] = -1;
                }
            }
        }

        //making all column -1 instead of zero
        for(int j = 0; j < m; j++){
            int flag = 0;
            for(int i = 0; i < n; i++){
                if(A[i][j]==0)flag = 1;
            }
            if(flag == 1){
                for(int i = 0; i < n; i++){
                    if(A[i][j] != 0) A[i][j] = -1;
                }
            }
        }

        //now replacing -1 to zero
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(A[i][j] == -1)A[i][j] = 0;
            }
        }
        //ans
        return A;
    }
}
