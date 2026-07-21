package com.kritsn.leetCodeJava.medium;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 */
public class _189RotateArray {

    static void rotate(int[] nums, int k) {
        int n = nums.length; // i.e. nums = [1, 2, 3, 4, 5, 6, 7],  k = 12 and n = nums.length = 7
        int steps = k % n; // Normalize k to be within array bounds // steps = 12 % 7 = 5

        // Step 1: Reverse the entire array
        reverse(nums, 0, n - 1); // nums = [7, 6, 5, 4, 3, 2, 1] after reverse

        // Step 2: Reverse the first k elements
        reverse(nums, 0, steps - 1); // nums = [3, 4, 5, 6, 7, 2, 1] after again reverse from 0 to( 5-1 = 4) subarray

        // Step 3: Reverse the remaining elements
        reverse(nums, steps, n - 1); // nums = [3, 4, 5, 6, 7, 1, 2] after again reverse from 5 to( 7-1 = 6) subarray
    }

    // Helper method to reverse a portion of the array in-place
    private static void reverse(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            // Swap elements at left and right indices
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    // 🔍 Main method with clearly labeled test cases and expected outputs
    public static void main(String[] args) {
        // Test Case 1: Standard rotation
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums1, 3);
        System.out.println("Test Case 1: After rotating [1,2,3,4,5,6,7] by 3 -> " + Arrays.toString(nums1)); // Expected: [5, 6, 7, 1, 2, 3, 4]

        // Test Case 2: Rotation by 0 (no change)
        int[] nums2 = {1, 2, 3};
        rotate(nums2, 0);
        System.out.println("Test Case 2: After rotating [1,2,3] by 0 -> " + Arrays.toString(nums2)); // Expected: [1, 2, 3]

        // Test Case 3: Rotation by array size (same as no rotation)
        int[] nums3 = {1, 2, 3, 4};
        rotate(nums3, 4);
        System.out.println("Test Case 3: After rotating [1,2,3,4] by 4 -> " + Arrays.toString(nums3)); // Expected: [1, 2, 3, 4]

        // Test Case 4: Rotation greater than array size
        int[] nums4 = {1, 2};
        rotate(nums4, 5);
        System.out.println("Test Case 4: After rotating [1,2] by 5 -> " + Arrays.toString(nums4)); // Expected: [2, 1]

        // Test Case 5: Single element
        int[] nums5 = {10};
        rotate(nums5, 100);
        System.out.println("Test Case 5: After rotating [10] by 100 -> " + Arrays.toString(nums5)); // Expected: [10]
    }
}
