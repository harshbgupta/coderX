package com.kritsn.leetCodeJava;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * 1. Each child must have at least one candy.
 * 2. Children with a higher rating get more candies than their neighbors.
 * <p>
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 */
public class _135Candy {

    ///////////////////////////////////////////////////////////////////////////
    // https://www.youtube.com/watch?v=IIqVFvKE6RY
    // Greedy Two-Pass Approach:
    // 1. Left-to-Right: If current child has higher rating than left neighbor,
    //    give one more candy than left neighbor.
    // 2. Right-to-Left: If current child has higher rating than right neighbor,
    //    ensure they have more candies than right neighbor.
    //
    // 🪜 Steps:
    // - Initialize candies array with all 1s.
    // - First pass: ratings[i] > ratings[i-1] → candies[i] = candies[i-1] + 1
    // - Second pass: ratings[i] > ratings[i+1] → candies[i] = max(candies[i], candies[i+1] + 1)
    // - Return total candies.
    ///////////////////////////////////////////////////////////////////////////
    int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        // Left to right pass
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Right to left pass
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int sum = 0;
        for (int c : candies) sum += c;
        return sum;
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _135Candy solver = new _135Candy();

        // Test Case 1: Ascending ratings
        int[] ratings1 = {1, 2, 3};
        System.out.println("Test Case 1: ratings = " + Arrays.toString(ratings1) + " -> Min Candies = " + solver.candy(ratings1)); // Expected: 6

        // Test Case 2: Descending ratings
        int[] ratings2 = {3, 2, 1};
        System.out.println("Test Case 2: ratings = " + Arrays.toString(ratings2) + " -> Min Candies = " + solver.candy(ratings2)); // Expected: 6

        // Test Case 3: Valley shape
        int[] ratings3 = {1, 0, 2};
        System.out.println("Test Case 3: ratings = " + Arrays.toString(ratings3) + " -> Min Candies = " + solver.candy(ratings3)); // Expected: 5

        // Test Case 4: Equal ratings
        int[] ratings4 = {1, 1, 1};
        System.out.println("Test Case 4: ratings = " + Arrays.toString(ratings4) + " -> Min Candies = " + solver.candy(ratings4)); // Expected: 3

        // Test Case 5: Random ratings
        int[] ratings5 = {1, 3, 4, 5, 2};
        System.out.println("Test Case 5: ratings = " + Arrays.toString(ratings5) + " -> Min Candies = " + solver.candy(ratings5)); // Expected: 11
    }
}
