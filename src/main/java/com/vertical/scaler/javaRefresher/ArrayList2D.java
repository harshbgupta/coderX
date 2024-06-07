package com.vertical.scaler.javaRefresher;

public class ArrayList2D {
    public static void main(String[] args) {

    }

    /**
     * You are given a N X N square integer matrix A. You have to tell whether A is an identity matrix or not.
     * <p>
     * Identity matrix is a special square matrix whose main diagonal elements are equal to 1 and all other elements are 0.
     */
    public static int q1(final int[][] A) {
        int row = A.length;
        int col = A[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i == j && A[i][j] != 1) || (i != j && A[i][j] != 0)) {
                    return 0;
                }
            }
        }
        return 1;

    }


    /**
     * You are given a matrix A, you have to return another matrix which is the transpose of A.
     * <p>
     * NOTE: Transpose of a matrix A is defined as - AT[i][j] = A[j][i] ; Where 1 ≤ i ≤ col and 1 ≤ j ≤ row
     */
    public int[][] q2(int[][] A) {
        int row = A.length, col = A[0].length;
        int[][] ans = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                ans[j][i] = A[i][j];

        }
        return ans;
    }

    /**
     * You are given two integer matrices A(having M X N size) and B(having N X P). You have to multiply matrix A with
     * B and return the resultant matrix. (i.e. return the matrix AB).
     */
    public int[][] q3(int[][] A, int[][] B) {
        int M = A.length;
        int N = A[0].length;
        int P = B[0].length;

        int[][] C = new int[M][P];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < P; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
        return C;
    }
}
