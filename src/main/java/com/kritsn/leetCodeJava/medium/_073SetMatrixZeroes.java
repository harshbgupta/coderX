package com.kritsn.leetCodeJava.medium;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 * Do it in-place.
 */
public class _073SetMatrixZeroes {

    ///////////////////////////////////////////////////////////////////////////
    // https://youtu.be/N0MgLvceX7M?feature=shared
    // In-Place Flagging Using First Row and Column:
    //
    // We use matrix[0][*] and matrix[*][0] as marker arrays instead of using extra space.
    //
    // 🪜 Steps:
    // 1. Check if first row or column contains 0 (store in firstRowZero/firstColZero).
    // 2. Use matrix[i][0] and matrix[0][j] to flag rows/columns that need zeroing.
    // 3. Iterate rest of matrix and zero if flagged.
    // 4. Finally, zero out first row and/or column if needed.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(m * n)
    // Space Complexity: O(1) — done in-place with no extra storage.
    ///////////////////////////////////////////////////////////////////////////
    void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check if first row contains any 0
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check if first column contains any 0
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Use first row and column as flags for zeroing
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Set colls to zero based on flags
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero first row if needed
        if (firstRowZero) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }

        // Zero first column if needed
        if (firstColZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        _073SetMatrixZeroes solver = new _073SetMatrixZeroes();

        int[][] matrix1 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        solver.setZeroes(matrix1);
        System.out.println("Test Case 1: Matrix after setting zeroes:");
        for (int[] row : matrix1) System.out.println(java.util.Arrays.toString(row));
        // Expected: [1,0,1], [0,0,0], [1,0,1]

        int[][] matrix2 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        solver.setZeroes(matrix2);
        System.out.println("\nTest Case 2: Matrix after setting zeroes:");
        for (int[] row : matrix2) System.out.println(java.util.Arrays.toString(row));
        // Expected: [0,0,0,0], [0,4,5,0], [0,3,1,0]
    }
}
