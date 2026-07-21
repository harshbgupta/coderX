package com.kritsn.leetCodeJava.medium;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a subarray whose sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 */
public class _209MinimumSizeSubarraySum {

    ///////////////////////////////////////////////////////////////////////////
    // Sliding Window Approach:
    //
    // Use two pointers to maintain a dynamic window [start, end].
    // Expand the window by moving 'end' until sum ≥ target.
    // Then, try to shrink it from the left to find the smallest valid window.
    //
    // 🪜 Steps:
    // 1. Initialize sum = 0, start = 0, minLen = Integer.MAX_VALUE.
    // 2. Move 'end' from 0 to n-1:
    //    - Add nums[end] to sum.
    //    - While sum >= target:
    //        - Update minLen = min(minLen, end - start + 1).
    //        - Subtract nums[start] from sum and increment start.
    // 3. If minLen was not updated, return 0. Else return minLen.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — each element is visited at most twice.
    // Space Complexity: O(1) — constant extra space.
    ///////////////////////////////////////////////////////////////////////////
    int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end]; // Add current element to sum

            // Shrink window from the left while sum is valid
            while (sum >= target) {
                minLen = Math.min(minLen, end - start + 1); // Update minimum length
                sum -= nums[start]; // Remove the leftmost element
                start++; // Move window start
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _209MinimumSizeSubarraySum solver = new _209MinimumSizeSubarraySum();

        int target1 = 7;
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        System.out.println("Test Case 1: target = " + target1 + ", nums = " + Arrays.toString(nums1) + " -> Min Length = " + solver.minSubArrayLen(target1, nums1)); // Expected: 2

        int target2 = 4;
        int[] nums2 = {1, 4, 4};
        System.out.println("Test Case 2: target = " + target2 + ", nums = " + Arrays.toString(nums2) + " -> Min Length = " + solver.minSubArrayLen(target2, nums2)); // Expected: 1

        int target3 = 11;
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println("Test Case 3: target = " + target3 + ", nums = " + Arrays.toString(nums3) + " -> Min Length = " + solver.minSubArrayLen(target3, nums3)); // Expected: 0

        int target4 = 15;
        int[] nums4 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 4: target = " + target4 + ", nums = " + Arrays.toString(nums4) + " -> Min Length = " + solver.minSubArrayLen(target4, nums4)); // Expected: 5

        int target5 = 100;
        int[] nums5 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 5: target = " + target5 + ", nums = " + Arrays.toString(nums5) + " -> Min Length = " + solver.minSubArrayLen(target5, nums5)); // Expected: 0
    }
}
