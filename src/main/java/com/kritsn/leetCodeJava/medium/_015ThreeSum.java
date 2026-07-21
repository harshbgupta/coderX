package com.kritsn.leetCodeJava.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * The solution set must not contain duplicate triplets.
 */
public class _015ThreeSum {

    ///////////////////////////////////////////////////////////////////////////
    // Sort + Two-Pointer Approach:
    //
    // After sorting, we fix one element and search the remaining array with two
    // pointers (left / right) to find complementary pairs that sum to zero.
    //
    // 🪜 Steps:
    // 1. Sort nums.
    // 2. For each index i (0..n-3):
    //    a. Skip duplicates for nums[i].
    //    b. Initialize left = i+1 and right = n-1.
    //    c. While left < right:
    //       - Compute sum = nums[i] + nums[left] + nums[right].
    //       - If sum == 0 -> store triplet, move both pointers (skip duplicates).
    //       - If sum < 0  -> left++ (need a larger sum).
    //       - If sum > 0  -> right-- (need a smaller sum).
    // 3. Return collected triplets.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n^2) — for each i we scan the rest with two pointers.
    // Space Complexity: O(1) extra — apart from output list (sorting is in place).
    ///////////////////////////////////////////////////////////////////////////
    List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;

        Arrays.sort(nums); // Step 1: sort in ascending order

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Found a valid triplet
                    result.add(List.of(nums[i], nums[left], nums[right]));

                    // Skip duplicates for left pointer
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // Skip duplicates for right pointer
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // Need a larger sum
                } else {
                    right--; // Need a smaller sum
                }
            }
        }
        return result;
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _015ThreeSum solver = new _015ThreeSum();

        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Test Case 1: nums = " + Arrays.toString(nums1) + " -> Triplets = " + solver.threeSum(nums1));
        // Expected: [[-1, -1, 2], [-1, 0, 1]]
    }
}
