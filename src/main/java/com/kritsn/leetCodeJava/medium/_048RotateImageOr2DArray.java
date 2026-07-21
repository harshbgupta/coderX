package com.kritsn.leetCodeJava.medium;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * Rotation must be done in-place—do not allocate another 2D matrix.
 */
public class _048RotateImageOr2DArray {

    /*
     * for 0 degree , do nothing
     * for 90 degree , do the following code once
     * for 180 degree , do the following code twice
     * for 270 degree , do the following code thrice
     * for 360 degree , do nothing
     */

    ///////////////////////////////////////////////////////////////////////////
    // Row‑Reverse + Transpose Technique:
    //
    // Step 1: Reverse rows (vertical flip).
    // Step 2: Transpose the matrix (swap across main diagonal).
    //
    // 🪜 Steps:
    // 1. For i in 0 until n/2:
    //      swap matrix[i] with matrix[n-1-i].
    // 2. For r in 0 until n:
    //      For c in r+1 until n:
    //         swap matrix[r][c] with matrix[c][r].
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n²) — we visit each element a constant number of times.
    // Space Complexity: O(1) — in‑place swaps only.
    ///////////////////////////////////////////////////////////////////////////
    void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) return;

        // Step 1: Reverse rows (top ↔ bottom)
        for (int i = 0; i < n / 2; i++) {
            int[] tmp = matrix[i];
            matrix[i] = matrix[n - 1 - i];
            matrix[n - 1 - i] = tmp;
        }

        // Step 2: Transpose in‑place across main diagonal
        for (int r = 0; r < n; r++) {
            for (int c = r + 1; c < n; c++) {
                int temp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = temp;
            }
        }
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _048RotateImageOr2DArray solver = new _048RotateImageOr2DArray();

        int[][] m1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Test Case 1 (3×3)");
        System.out.println("Original Matrix →");
        for (int[] row : m1) System.out.println(java.util.Arrays.toString(row));
        solver.rotate(m1);
        System.out.println("Final Result (3×3): Rotated Matrix →");
        for (int[] row : m1) System.out.println(java.util.Arrays.toString(row));
        // Expected: [7,4,1] [8,5,2] [9,6,3]

        int[][] m2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        System.out.println("Test Case 1 (3×3)");
        System.out.println("Original Matrix →");
        for (int[] row : m2) System.out.println(java.util.Arrays.toString(row));
        solver.rotate(m2);
        System.out.println("Final Result (3×3): Rotated Matrix →");
        for (int[] row : m2) System.out.println(java.util.Arrays.toString(row));
        // Expected: [15,13,2,5] [14,3,4,1] [12,6,8,9] [16,7,10,11]
    }
}
