package com.kritsn.leetCodeJava;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 */
public class _054SpiralMatrix {

    ///////////////////////////////////////////////////////////////////////////
    // Boundary Layer Traversal Approach:
    //
    // We simulate the spiral path by tracking four boundaries: top, bottom,
    // left, and right. After each direction of traversal, we adjust boundaries
    // inward and continue until all layers are covered.
    //
    // 🪜 Steps:
    // 1. Initialize pointers: top = 0, bottom = m-1, left = 0, right = n-1
    // 2. Loop until all boundaries cross:
    //    a. Traverse from left to right → top++
    //    b. Traverse from top to bottom → right--
    //    c. Traverse from right to left → bottom-- (if top ≤ bottom)
    //    d. Traverse from bottom to top → left++ (if left ≤ right)
    // 3. Collect elements in each step into result list.
    // 4. Return the list.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(m * n) — each element is visited once.
    // Space Complexity: O(1) extra — excluding output list.
    ///////////////////////////////////////////////////////////////////////////
    List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // Step 1: Traverse from left to right along top row or go left to right
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++; // move top boundary down

            // Step 2: Traverse from top to bottom along right column or top to bottom
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--; // move right boundary left

            // Step 3: Traverse from right to left along bottom row or right to left
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--; // move bottom boundary up
            }

            // Step 4: Traverse from bottom to top along left column or bottom to top
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++; // move left boundary right
            }
        }

        return result;
    }

    public static void main(String[] args) {
        _054SpiralMatrix solver = new _054SpiralMatrix();

        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("Test Case 1: matrix = " + java.util.Arrays.deepToString(matrix1) + " -> Spiral Order = " + solver.spiralOrder(matrix1));
        // Expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]

        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.println("Test Case 2: matrix = " + java.util.Arrays.deepToString(matrix2) + " -> Spiral Order = " + solver.spiralOrder(matrix2));
        // Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

        int[][] matrix3 = {
                {1}
        };
        System.out.println("Test Case 3: matrix = " + java.util.Arrays.deepToString(matrix3) + " -> Spiral Order = " + solver.spiralOrder(matrix3));
        // Expected: [1]

        int[][] matrix4 = {
                {1, 2},
                {3, 4}
        };
        System.out.println("Test Case 4: matrix = " + java.util.Arrays.deepToString(matrix4) + " -> Spiral Order = " + solver.spiralOrder(matrix4));
        // Expected: [1, 2, 4, 3]
    }
}
