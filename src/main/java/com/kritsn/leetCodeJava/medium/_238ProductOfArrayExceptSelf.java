package com.kritsn.leetCodeJava.medium;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jun 14, 2026
 */
/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the
 * product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 */
public class _238ProductOfArrayExceptSelf {

    ///////////////////////////////////////////////////////////////////////////
    // Two-pass approach:
    // 1. First pass (left to right): Fill result[i] with product of all elements to the left.
    // 2. Second pass (right to left): Multiply result[i] with product of all elements to the right.
    //
    // 🪜 Steps:
    // - Initialize answer[] with all 1s.
    // - Compute left products in first pass.
    // - Compute right products in second pass while updating answer[].
    ///////////////////////////////////////////////////////////////////////////
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        int leftProduct = 1;
        for (int i = 0; i < n; i++) {
            answer[i] = leftProduct;
            leftProduct *= nums[i];
        }

        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return answer;
    }

    public int[] productExceptSelfWrong(int[] nums) {
        Long product = 1L;
        int[] res = new int[nums.length];
        Boolean[] zeros = new Boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                product *= nums[i];
                zeros[i] = false;
            } else  {
                zeros[i] = true;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (zeros[i]) {
                res[i] = Integer.parseInt((product ) + "");
            } else {
                res[i] = Integer.parseInt((product / nums[i]) + "");
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _238ProductOfArrayExceptSelf solver = new _238ProductOfArrayExceptSelf();

        // Test Case 1: General case
        int[] nums1 = {1, 2, 3, 4};
        System.out.println("Test Case 1: nums = " + Arrays.toString(nums1)
                + " -> answer = " + Arrays.toString(solver.productExceptSelf(nums1)));
        // Expected: [24, 12, 8, 6]

        // Test Case 2: Includes a zero
        int[] nums2 = {1, 2, 0, 4};
        System.out.println("Test Case 2: nums = " + Arrays.toString(nums2)
                + " -> answer = " + Arrays.toString(solver.productExceptSelf(nums2)));
        // Expected: [0, 0, 8, 0]

        // Test Case 3: All same elements
        int[] nums3 = {2, 2, 2, 2};
        System.out.println("Test Case 3: nums = " + Arrays.toString(nums3)
                + " -> answer = " + Arrays.toString(solver.productExceptSelf(nums3)));
        // Expected: [8, 8, 8, 8]

        // Test Case 4: Two elements
        int[] nums4 = {10, 1};
        System.out.println("Test Case 4: nums = " + Arrays.toString(nums4)
                + " -> answer = " + Arrays.toString(solver.productExceptSelf(nums4)));
        // Expected: [1, 10]

        // Test Case 5: Contains multiple zeros
        int[] nums5 = {0, 0, 3, 4};
        System.out.println("Test Case 5: nums = " + Arrays.toString(nums5)
                + " -> answer = " + Arrays.toString(solver.productExceptSelf(nums5)));
        // Expected: [0, 0, 0, 0]
    }
}
