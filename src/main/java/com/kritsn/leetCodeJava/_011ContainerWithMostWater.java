package com.kritsn.leetCodeJava;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 05, 2025
 */

/*
 * You are given an integer array height of length n.
 * There are n vertical lines such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 */
public class _011ContainerWithMostWater {

    ///////////////////////////////////////////////////////////////////////////
    // Two-Pointer Optimization:
    //
    // We scan from both ends toward the center:
    // - Calculate area between current pointers.
    // - Move the pointer pointing to the shorter line inward.
    //
    // 🪜 Steps:
    // 1. Initialize two pointers: left = 0, right = height.size - 1
    // 2. While left < right:
    //    - width = right - left
    //    - height = min(height[left], height[right])
    //    - area = width * height
    //    - update maxArea
    //    - move the pointer pointing to smaller height
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — Single pass through the array.
    // Space Complexity: O(1) — Constant space.
    ///////////////////////////////////////////////////////////////////////////
    private int containedMaxWater(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int maxArea = 0;

        while (left < right) {
            // Calculate width and height of current container
            int width  = right -left;
            int containerHeight = Math.min(height[left], height[right]);
            int area = width * containerHeight;
            // Update the maximum area if needed
            maxArea = Math.max(maxArea, area);

            // Move the pointer with the smaller height inward
            if (height[left] < height[right]) {
                // need to find bigger left wall
                left++;
            } else {
                // need to find bigger left wall
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        _011ContainerWithMostWater solution = new _011ContainerWithMostWater();
        System.out.println(solution.containedMaxWater(new int[]{1,8,6,2,5,4,8,3,7} ));
    }
}
