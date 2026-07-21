package com.kritsn.leetCodeJava.medium;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 */
public class _055JumpGame {

    ///////////////////////////////////////////////////////////////////////////
    // We track the farthest index we can reach as we iterate through the array.
    // If at any point our current index is greater than the farthest reachable,
    // it means we’re stuck and return false.
    //
    // 🪜 Steps:
    // 1. Initialize maxReachable = 0.
    // 2. For each index i in nums:
    //    - If i > maxReachable => we can’t reach this point => return false.
    //    - Update maxReachable = max(maxReachable, i + nums[i])
    // 3. If we finish iterating, return true.
    ///////////////////////////////////////////////////////////////////////////
    boolean canJump(int[] nums) {
        int maxReachable = 0; // Track the farthest index we can reach

        for (int i = 0; i < nums.length; i++) {
            if (i > maxReachable) {
                // We are at an index that is not reachable
                return false;
            }
            // Update the farthest index we can reach so far
            maxReachable = Math.max(maxReachable, i + nums[i]);
        }

        // If loop completes, we can reach the last index
        return true;
    }

    public static void main(String[] args) {
        _055JumpGame solver = new _055JumpGame();

        // Test Case 1: Can jump over zero safely
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Test Case 1: nums = " + Arrays.toString(nums1) + " -> Can reach end = " + solver.canJump(nums1)); // Expected: true

        // Test Case 2: Stuck at a zero
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Test Case 2: nums = " + Arrays.toString(nums2) + " -> Can reach end = " + solver.canJump(nums2)); // Expected: false

        // Test Case 3: Single element array
        int[] nums3 = {0};
        System.out.println("Test Case 3: nums = " + Arrays.toString(nums3) + " -> Can reach end = " + solver.canJump(nums3)); // Expected: true

        // Test Case 4: All large jumps
        int[] nums4 = {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0};
        System.out.println("Test Case 4: nums = " + Arrays.toString(nums4) + " -> Can reach end = " + solver.canJump(nums4)); // Expected: true

        // Test Case 5: Just enough jump
        int[] nums5 = {2, 0, 0};
        System.out.println("Test Case 5: nums = " + Arrays.toString(nums5) + " -> Can reach end = " + solver.canJump(nums5)); // Expected: true
    }
}
