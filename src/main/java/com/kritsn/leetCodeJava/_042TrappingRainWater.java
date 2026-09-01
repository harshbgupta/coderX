package com.kritsn.leetCodeJava;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 */
public class _042TrappingRainWater {

    ///////////////////////////////////////////////////////////////////////////
    // https://youtu.be/1_5VuquLbXg?feature=shared
    // https://youtu.be/UHHp8USwx4M?feature=shared
    // Prefix Max Approach:
    // For each index, the trapped water is:
    // min(maxLeft[i], maxRight[i]) - height[i]
    //
    // 🪜 Steps:
    // 1. Build leftMax[] from left to right.
    // 2. Build rightMax[] from right to left.
    // 3. For each index i:
    //    - waterAtI = min(leftMax[i], rightMax[i]) - height[i]
    //    - Add waterAtI to total
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — One pass for leftMax, one for rightMax, and one to calculate result.
    // Space Complexity: O(n) — Extra space for leftMax and rightMax arrays.
    // (Can be optimized to O(1) space using two pointers approach.)

    /// ////////////////////////////////////////////////////////////////////////
    int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Build left max array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // Build right max array
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // Calculate trapped water
        int water = 0;
        for (int i = 0; i < n; i++) {
            int minHeight = Math.min(leftMax[i], rightMax[i]);
            if (minHeight > height[i]) {
                water += minHeight - height[i];
            }
        }

        return water;
    }

    public int trapOptimal(int[] height) {
        if (height.length < 3) return 0;

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                // Left is lower, process left
                water += leftMax - height[left];
                left++;
            } else {
                // Right is lower or equal, process right
                water += rightMax - height[right];
                right--;
            }
        }

        return water;
    }

    public static void main(String[] args) {
        _042TrappingRainWater solver = new _042TrappingRainWater();

        // Test Case 1: Standard case
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Test Case 1: height = " + Arrays.toString(height1) + " -> Trapped Water = " + solver.trapOptimal(height1)); // Expected: 6

        // Test Case 2: No valleys
        int[] height2 = {3, 3, 3, 3};
        System.out.println("Test Case 2: height = " + Arrays.toString(height2) + " -> Trapped Water = " + solver.trapOptimal(height2)); // Expected: 0

        // Test Case 3: Single valley
        int[] height3 = {4, 2, 0, 3, 2, 5};
        System.out.println("Test Case 3: height = " + Arrays.toString(height3) + " -> Trapped Water = " + solver.trapOptimal(height3)); // Expected: 9

        // Test Case 4: Increasing then decreasing
        int[] height4 = {1, 2, 3, 4, 3, 2, 1};
        System.out.println("Test Case 4: height = " + Arrays.toString(height4) + " -> Trapped Water = " + solver.trapOptimal(height4)); // Expected: 0

        // Test Case 5: Empty input
        int[] height5 = {};
        System.out.println("Test Case 5: height = " + Arrays.toString(height5) + " -> Trapped Water = " + solver.trapOptimal(height5)); // Expected: 0
    }
}
