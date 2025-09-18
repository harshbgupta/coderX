package com.kritsn.leetCodeJava;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 18, 2025
 */
/**
 * LeetCode 1004. Max Consecutive Ones III
 *
 * Problem:
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1s in the array
 * if you can flip at most k 0s.
 *
 * Examples:
 * 1) nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2 -> 6
 * 2) nums = [0,0,1,1,0,0,1,1,1,0], k = 3 -> 10
 *
 * Constraints:
 * - 1 <= nums.length <= 1e5
 * - nums[i] ∈ {0,1}
 * - 0 <= k <= nums.length
 */
public class _1004MaxConsecutiveOnesIII {

    /**
     * Sliding Window (Two Pointers)
     *
     * Intuition:
     * Maintain a window [left, right] such that the number of zeros inside it is <= k.
     * Expand right, count zeros; if zeros exceed k, move left forward and decrement zero count when we pass a zero.
     * Track the maximum window size encountered.
     *
     * Correctness:
     * At any time, the window is the longest valid window ending at 'right' with at most k zeros.
     * Moving 'left' only when needed ensures maximality without missing candidates.
     *
     * Complexity:
     * - Time: O(n), each index enters/exits the window at most once.
     * - Space: O(1).
     */
    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int best = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeroCount++;

            // Shrink while window invalid (too many zeros)
            while (zeroCount > k) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }
            // Window [left..right] is valid -> update answer
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    // --------- Demo ----------
    public static void main(String[] args) {
        System.out.println("Case 1: " + longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2) + " (expected 6)");
        System.out.println("Case 2: " + longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0}, 3) + " (expected 10)");
        System.out.println("All ones, k=0: " + longestOnes(new int[]{1,1,1,1}, 0) + " (expected 4)");
        System.out.println("All zeros, k=3: " + longestOnes(new int[]{0,0,0,0,0}, 3) + " (expected 3)");
        System.out.println("Single elem [0], k=1: " + longestOnes(new int[]{0}, 1) + " (expected 1)");
    }
}