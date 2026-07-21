package com.kritsn.leetCodeJava.easy;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

public class _169MajorityElements {

    /**
     * Given an array nums of size n, return the majority element.
     * <p>
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element
     * always exists in the array.
     */
    static int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                // Set the current element as candidate
                candidate = num;
            }

            // If the current number matches the candidate, increase the count
            // Else decrease the count
            count += (num == candidate) ? 1 : -1;
        }

        // Since majority element always exists, return candidate
        return candidate;
    }

    // 🔍 Test the code with multiple cases
    public static void main(String[] args) {
        // Test Case 1: Simple case with obvious majority
        int[] nums1 = {3, 4, 5, 2, 3, 4, 4, 5, 5};
        System.out.println("Test Case 1: Input = " + Arrays.toString(nums1) + ", Output = " + majorityElement(nums1)); // Expected: 3

        // Test Case 2: Majority is spread
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Test Case 2: Input = " + Arrays.toString(nums2) + ", Output = " + majorityElement(nums2)); // Expected: 2

        // Test Case 3: All elements same
        int[] nums3 = {5, 5, 5, 5};
        System.out.println("Test Case 3: Input = " + Arrays.toString(nums3) + ", Output = " + majorityElement(nums3)); // Expected: 5

        // Test Case 4: Single element
        int[] nums4 = {9};
        System.out.println("Test Case 4: Input = " + Arrays.toString(nums4) + ", Output = " + majorityElement(nums4)); // Expected: 9
    }
}
