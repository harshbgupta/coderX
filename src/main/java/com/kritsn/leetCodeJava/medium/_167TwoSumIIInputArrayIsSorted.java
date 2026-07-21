package com.kritsn.leetCodeJava.medium;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 * <p>
 * Return the indices of the two numbers (1-based), [index1, index2], as an array.
 * <p>
 * The solution must use only constant extra space.
 */
public class _167TwoSumIIInputArrayIsSorted {

    ///////////////////////////////////////////////////////////////////////////
    // Two-Pointer Technique:
    //
    // Since the array is sorted, we can use left and right pointers.
    // - Move the pointers based on the current sum.
    // - If current sum < target → move left pointer right
    // - If current sum > target → move right pointer left
    // - If equal → return indices (1-based)
    //
    // 🪜 Steps:
    // 1. Set left = 0, right = numbers.size - 1
    // 2. While left < right:
    //    - sum = numbers[left] + numbers[right]
    //    - Adjust pointers accordingly
    // 3. Return result when found
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — single pass using two pointers
    // Space Complexity: O(1) — constant space
    ///////////////////////////////////////////////////////////////////////////
    int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1}; // Return 1-based index
            } else if (sum < target) {
                left++;  // Move left forward to increase sum
            } else {
                right--; // Move right backward to decrease sum
            }
        }

        return new int[]{}; // Should never reach here as one solution is guaranteed
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _167TwoSumIIInputArrayIsSorted solver = new _167TwoSumIIInputArrayIsSorted();

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test Case 1: nums = " + Arrays.toString(nums1) + ", target = " + target1 + " -> Output = " + Arrays.toString(solver.twoSum(nums1, target1))); // Expected: [1, 2]

        int[] nums2 = {2, 3, 4};
        int target2 = 6;
        System.out.println("Test Case 2: nums = " + Arrays.toString(nums2) + ", target = " + target2 + " -> Output = " + Arrays.toString(solver.twoSum(nums2, target2))); // Expected: [1, 3]

        int[] nums3 = {-1, 0};
        int target3 = -1;
        System.out.println("Test Case 3: nums = " + Arrays.toString(nums3) + ", target = " + target3 + " -> Output = " + Arrays.toString(solver.twoSum(nums3, target3))); // Expected: [1, 2]

        int[] nums4 = {1, 3, 4, 6, 8, 10, 12};
        int target4 = 14;
        System.out.println("Test Case 4: nums = " + Arrays.toString(nums4) + ", target = " + target4 + " -> Output = " + Arrays.toString(solver.twoSum(nums4, target4))); // Expected: [3, 6]
    }
}
