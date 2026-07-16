package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 16, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 * Do it in-place.
 */
class _073SetMatrixZeroes {

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
    fun setZeroes(matrix: Array<IntArray>) {
        val rows = matrix.size
        val cols = matrix[0].size

        var firstRowZero = false
        var firstColZero = false

        // Check if first row contains any 0
        for (j in 0 until cols) {
            if (matrix[0][j] == 0) {
                firstRowZero = true
                break
            }
        }

        // Check if first column contains any 0
        for (i in 0 until rows) {
            if (matrix[i][0] == 0) {
                firstColZero = true
                break
            }
        }

        // Use first row and column as flags for zeroing
        for (i in 1 until rows) {
            for (j in 1 until cols) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0
                    matrix[0][j] = 0
                }
            }
        }

        // Set colls to zero based on flags
        for (i in 1 until rows) {
            for (j in 1 until cols) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0
                }
            }
        }

        // Zero first row if needed
        if (firstRowZero) {
            for (j in 0 until cols) {
                matrix[0][j] = 0
            }
        }

        // Zero first column if needed
        if (firstColZero) {
            for (i in 0 until rows) {
                matrix[i][0] = 0
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _073SetMatrixZeroes()

            val matrix1 = arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 0, 1),
                intArrayOf(1, 1, 1)
            )
            solver.setZeroes(matrix1)
            println("Test Case 1: Matrix after setting zeroes:")
            matrix1.forEach { println(it.contentToString()) }
            // Expected: [1,0,1], [0,0,0], [1,0,1]

            val matrix2 = arrayOf(
                intArrayOf(0, 1, 2, 0),
                intArrayOf(3, 4, 5, 2),
                intArrayOf(1, 3, 1, 5)
            )
            solver.setZeroes(matrix2)
            println("\nTest Case 2: Matrix after setting zeroes:")
            matrix2.forEach { println(it.contentToString()) }
            // Expected: [0,0,0,0], [0,4,5,0], [0,3,1,0]
        }
    }
}