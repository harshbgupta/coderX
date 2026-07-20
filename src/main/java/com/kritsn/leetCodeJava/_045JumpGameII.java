package com.kritsn.leetCodeJava;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * You are given a 0-indexed array of integers nums of length n.
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * <p>
 * Return the minimum number of jumps to reach nums[n - 1]. You can assume that it is always reachable.
 */
public class _045JumpGameII {

    ///////////////////////////////////////////////////////////////////////////
    // Greedy Approach:
    // - Track the farthest point reachable from current index.
    // - Track the end of the current jump range.
    // - Every time we reach the end of the current jump range, we increment the jump count.
    //
    // 🪜 Steps:
    // 1. Initialize variables: jumps = 0, currentEnd = 0, farthest = 0.
    // 2. Traverse from index 0 to n-2 (we never jump from the last index).
    // 3. At each step, update farthest = max(farthest, i + nums[i]).
    // 4. If i == currentEnd:
    //    - Increment jumps and update currentEnd = farthest.
    // 5. Return total jumps.
    ///////////////////////////////////////////////////////////////////////////
    int jump(int[] nums) {
        int jumps = 0;      // Count of jumps needed
        int currentEnd = 0; // End of the current jump range
        int farthest = 0;   // Farthest index we can reach in current scope

        for (int i = 0; i < nums.length - 1; i++) {
            // Update the farthest we can reach from current index
            farthest = Math.max(farthest, i + nums[i]);

            // If we reached the end of the current jump, increase jump count
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest; // Move to the next range
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        _045JumpGameII solver = new _045JumpGameII();

        // Test Case 1: Classic jump case
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Test Case 1: nums = " + Arrays.toString(nums1) + " -> Min Jumps = " + solver.jump(nums1)); // Expected: 2

        // Test Case 2: One jump is enough
        int[] nums2 = {2, 1};
        System.out.println("Test Case 2: nums = " + Arrays.toString(nums2) + " -> Min Jumps = " + solver.jump(nums2)); // Expected: 1

        // Test Case 3: Increasing jumps
        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 3: nums = " + Arrays.toString(nums3) + " -> Min Jumps = " + solver.jump(nums3)); // Expected: 3

        // Test Case 4: Large jump at the start
        int[] nums4 = {10, 1, 1, 1, 1};
        System.out.println("Test Case 4: nums = " + Arrays.toString(nums4) + " -> Min Jumps = " + solver.jump(nums4)); // Expected: 1

        // Test Case 5: All 1s
        int[] nums5 = new int[6];
        Arrays.fill(nums5, 1);
        System.out.println("Test Case 5: nums = " + Arrays.toString(nums5) + " -> Min Jumps = " + solver.jump(nums5)); // Expected: 5
    }
}
