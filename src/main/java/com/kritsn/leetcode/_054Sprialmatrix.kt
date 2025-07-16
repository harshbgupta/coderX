package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 16, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 */
class _054SpiralMatrix {

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
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        if (matrix.isEmpty()) return result
        var (top, bottom) = 0 to matrix.size - 1
        var (left, right) = 0 to matrix[0].size - 1
//        var (varOne,varTwo, varThree) = Triple(0 , 5 , 10) // this is just an example to show how can declare Triple values like we declare top, bottom, left and right

        while (top <= bottom && left <= right) {

            // Step 1: Traverse from left to right along top row or go left to right
            for (col in left..right) {
                result.add(matrix[top][col])
            }
            top++ // move top boundary down

            // Step 2: Traverse from top to bottom along right column or top to bottom
            for (row in top..bottom) {
                result.add(matrix[row][right])
            }
            right-- // move right boundary left

            // Step 3: Traverse from right to left along bottom row or right to left
            if (top <= bottom) {
                for (col in right downTo left) {
                    result.add(matrix[bottom][col])
                }
                bottom-- // move bottom boundary up
            }

            // Step 4: Traverse from bottom to top along left column or bottom to top
            if (left <= right) {
                for (row in bottom downTo top) {
                    result.add(matrix[row][left])
                }
                left++ // move left boundary right
            }
        }

        return result
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
//        var (varOne,varTwo, varThree) = Triple(0 , 5 , 10) // this is just an example to show how can declare Triple values like we declare top, bottom, left and right

    val solver = _054SpiralMatrix()

    val matrix1 = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )
    println("Test Case 1: matrix = ${matrix1.contentDeepToString()} -> Spiral Order = ${solver.spiralOrder(matrix1)}")
    // Expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]

    val matrix2 = arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 7, 8),
        intArrayOf(9, 10, 11, 12)
    )
    println("Test Case 2: matrix = ${matrix2.contentDeepToString()} -> Spiral Order = ${solver.spiralOrder(matrix2)}")
    // Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

    val matrix3 = arrayOf(
        intArrayOf(1)
    )
    println("Test Case 3: matrix = ${matrix3.contentDeepToString()} -> Spiral Order = ${solver.spiralOrder(matrix3)}")
    // Expected: [1]

    val matrix4 = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(3, 4)
    )
    println("Test Case 4: matrix = ${matrix4.contentDeepToString()} -> Spiral Order = ${solver.spiralOrder(matrix4)}")
    // Expected: [1, 2, 4, 3]
}
