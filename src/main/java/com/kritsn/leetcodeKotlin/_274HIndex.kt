package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 12, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper,
 * return the researcher's h-index.
 *
 * The h-index is defined as the maximum value of h such that the given researcher has published
 * at least h papers that have each been cited at least h times.
 */
class _274HIndex {

    ///////////////////////////////////////////////////////////////////////////
    // Sort the array in descending order and find the largest h such that
    // at least h papers have ≥ h citations.
    //
    // 🪜 Steps:
    // 1. Sort citations in descending order.
    // 2. Traverse the sorted array.
    //    - For index i, check if citations[i] >= i + 1.
    //    - If so, update h = i + 1.
    //    - Else, break (condition no longer holds).
    // 3. Return the final h.
    ///////////////////////////////////////////////////////////////////////////
    fun hIndex(citations: IntArray): Int {
        // Step 1: Sort in descending order
        val sorted = citations.sortedDescending()

        var h = 0 // Initialize h-index
        for (i in sorted.indices) {
            if (sorted[i] >= i + 1) {
                h = i + 1 // Update h if condition is satisfied
            } else {
                break // No further h-index possible
            }
        }

        return h
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _274HIndex()

    // Test Case 1: Mixed citations
    val citations1 = intArrayOf(3, 0, 6, 1, 5)
    println("Test Case 1: citations = ${citations1.contentToString()} -> h-index = ${solver.hIndex(citations1)}") // Expected: 3

    // Test Case 2: All zero citations
    val citations2 = intArrayOf(0, 0, 0, 0)
    println("Test Case 2: citations = ${citations2.contentToString()} -> h-index = ${solver.hIndex(citations2)}") // Expected: 0

    // Test Case 3: Sorted descending
    val citations3 = intArrayOf(10, 8, 5, 4, 3)
    println("Test Case 3: citations = ${citations3.contentToString()} -> h-index = ${solver.hIndex(citations3)}") // Expected: 4

    // Test Case 4: Single paper
    val citations4 = intArrayOf(100)
    println("Test Case 4: citations = ${citations4.contentToString()} -> h-index = ${solver.hIndex(citations4)}") // Expected: 1

    // Test Case 5: Multiple high citations, low papers
    val citations5 = intArrayOf(25, 8, 5, 3, 3)
    println("Test Case 5: citations = ${citations5.contentToString()} -> h-index = ${solver.hIndex(citations5)}") // Expected: 3
}
