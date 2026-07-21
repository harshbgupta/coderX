package com.kritsn.leetCodeJava.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Leetcode 128: Longest Consecutive Sequence
 * <p>
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 */
public class _128LongestConsecutiveSequence {

    /**
     * 🧠 Algorithm & Approach:
     * <p>
     * 1. Store all elements in a HashSet for O(1) lookups.
     * 2. Loop through each number in the array.
     * - Only start counting a new sequence if (num - 1) doesn't exist in the set.
     * - From there, increment num + 1, num + 2, ... while they exist in the set.
     * - Track the max sequence length seen so far.
     * <p>
     * Time Complexity: O(n), where n is the number of elements.
     * Space Complexity: O(n), for the HashSet.
     */
    int longestConsecutive(int[] nums) {
        // Edge case: if the input array is empty, return 0 immediately
        if (nums.length == 0) return 0;

        // Convert the array to a HashSet to allow O(1) time complexity for lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num);

        // Variable to keep track of the maximum length of any consecutive sequence found
        int maxLength = 0;

        // Iterate through each number in the array
        for (int num : nums) {
            // Optimization: Only consider 'num' as the start of a sequence
            // if the number just before it (num - 1) does NOT exist in the set.
            // This ensures we only build sequences from their beginning,
            // avoiding redundant work and keeping time complexity linear.
            if (!numSet.contains(num - 1)) {

                // Start a new sequence from the current number
                int currentNum = num;
                int currentStreak = 1; // Initial length of the streak is 1 (currentNum itself)

                // Keep checking for the next consecutive number in the sequence
                // As long as (currentNum + 1) exists in the set, continue the streak
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1; // Move to the next consecutive number
                    currentStreak += 1; // Increase the streak length
                }

                // Update the maximum length found so far, if this streak is longer
                maxLength = Math.max(maxLength, currentStreak);
            }
        }

        // Return the longest consecutive sequence length found
        return maxLength;
    }

    // 🧪 Main method with test cases
    public static void main(String[] args) {
        _128LongestConsecutiveSequence solution = new _128LongestConsecutiveSequence();

        System.out.println("Test Case 1:");
        System.out.println("Input: [100, 4, 200, 1, 3, 2]");
        System.out.println("Output: " + solution.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2})); // Expected: 4

        System.out.println("\nTest Case 2:");
        System.out.println("Input: [0,3,7,2,5,8,4,6,0,1]");
        System.out.println("Output: " + solution.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})); // Expected: 9

        System.out.println("\nTest Case 3:");
        System.out.println("Input: [9,1,-3,2,4,8,3,-1,6,-2,-4,7]");
        System.out.println("Output: " + solution.longestConsecutive(new int[]{9, 1, -3, 2, 4, 8, 3, -1, 6, -2, -4, 7})); // Expected: 7

        System.out.println("\nTest Case 4:");
        System.out.println("Input: [1,2,0,1]");
        System.out.println("Output: " + solution.longestConsecutive(new int[]{1, 2, 0, 1})); // Expected: 3
    }
}
