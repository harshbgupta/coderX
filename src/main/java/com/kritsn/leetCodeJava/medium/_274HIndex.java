package com.kritsn.leetCodeJava.medium;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper,
 * return the researcher's h-index.
 * <p>
 * The h-index is defined as the maximum value of h such that the given researcher has published
 * at least h papers that have each been cited at least h times.
 */
public class _274HIndex {

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
    int hIndex(int[] citations) {
        // Step 1: Sort in descending order
        Integer[] sorted = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(sorted, (a, b) -> b - a);

        int h = 0; // Initialize h-index
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i] >= i + 1) {
                h = i + 1; // Update h if condition is satisfied
            } else {
                break; // No further h-index possible
            }
        }

        return h;
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _274HIndex solver = new _274HIndex();

        // Test Case 1: Mixed citations
        int[] citations1 = {3, 0, 6, 1, 5};
        System.out.println("Test Case 1: citations = " + Arrays.toString(citations1) + " -> h-index = " + solver.hIndex(citations1)); // Expected: 3

        // Test Case 2: All zero citations
        int[] citations2 = {0, 0, 0, 0};
        System.out.println("Test Case 2: citations = " + Arrays.toString(citations2) + " -> h-index = " + solver.hIndex(citations2)); // Expected: 0

        // Test Case 3: Sorted descending
        int[] citations3 = {10, 8, 5, 4, 3};
        System.out.println("Test Case 3: citations = " + Arrays.toString(citations3) + " -> h-index = " + solver.hIndex(citations3)); // Expected: 4

        // Test Case 4: Single paper
        int[] citations4 = {100};
        System.out.println("Test Case 4: citations = " + Arrays.toString(citations4) + " -> h-index = " + solver.hIndex(citations4)); // Expected: 1

        // Test Case 5: Multiple high citations, low papers
        int[] citations5 = {25, 8, 5, 3, 3};
        System.out.println("Test Case 5: citations = " + Arrays.toString(citations5) + " -> h-index = " + solver.hIndex(citations5)); // Expected: 3
    }
}
