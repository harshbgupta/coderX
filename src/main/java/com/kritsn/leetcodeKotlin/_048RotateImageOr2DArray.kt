package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 16, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * Rotation must be done in-place—do not allocate another 2D matrix.
 */
class _048RotateImageOr2DArray {

    /**
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
    fun rotate(matrix: Array<IntArray>) {
        val n = matrix.size
        if (n <= 1) return

        // Step 1: Reverse rows (top ↔ bottom)
        for (i in 0 until n / 2) {
            val tmp = matrix[i]
            matrix[i] = matrix[n - 1 - i]
            matrix[n - 1 - i] = tmp
        }
//        println("Reverse Rows 1st Stage (top ↔ bottom) Matrix (3×3): →")
//        matrix.forEach { println(it.contentToString()) }
        // Step 2: Transpose in‑place across main diagonal
        for (r in 0 until n) {
            for (c in r + 1 until n) {
                val temp = matrix[r][c]
                matrix[r][c] = matrix[c][r]
                matrix[c][r] = temp
            }
        }
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _048RotateImageOr2DArray()

    val m1 = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )

    println("Test Case 1 (3×3)")
    println("Original Matrix →")
    m1.forEach { println(it.contentToString()) }
    solver.rotate(m1)
    println("Final Result (3×3): Rotated Matrix →")
    m1.forEach { println(it.contentToString()) }
    // Expected: [7,4,1] [8,5,2] [9,6,3]

    val m2 = arrayOf(
        intArrayOf(5, 1, 9,11),
        intArrayOf(2, 4, 8,10),
        intArrayOf(13, 3, 6, 7),
        intArrayOf(15,14,12,16)
    )
    println("Test Case 1 (3×3)")
    println("Original Matrix →")
    m2.forEach { println(it.contentToString()) }
    solver.rotate(m2)
    println("Final Result (3×3): Rotated Matrix →")
    m2.forEach { println(it.contentToString()) }
    // Expected: [15,13,2,5] [14,3,4,1] [12,6,8,9] [16,7,10,11]
}
