package com.kritsn.leetCodeJava.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
    Leetcode 219: Contains Duplicate II

    Given an integer array nums and an integer k, return true if there are two distinct indices i and j
    such that nums[i] == nums[j] and abs(i - j) <= k.
*/
public class _219ContainsDuplicateII {

    /**
     * 🧠 Algorithm & Approach:
     * <p>
     * 1. Use a HashSet to store at most k recent elements (sliding window).
     * 2. As we iterate through the array:
     * - If the element already exists in the set, it means a duplicate is found within k distance.
     * - Add current element to the set.
     * - Remove the (i - k)th element if window exceeds size k.
     * <p>
     * Time Complexity: O(n), where n is the length of the array.
     * Space Complexity: O(k), for storing up to k elements in the HashSet.
     */
    boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> windowSet = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (windowSet.contains(nums[i])) {
                return true;
            }

            windowSet.add(nums[i]);

            // Maintain sliding window of size at most k
            if (windowSet.size() > k) {
                windowSet.remove(nums[i - k]);
            }
        }

        return false;
    }

    // 🧪 Main method with test cases
    public static void main(String[] args) {
        _219ContainsDuplicateII solution = new _219ContainsDuplicateII();

        System.out.println("Test Case 1:");
        System.out.println("Input: nums = [1,2,3,1], k = 3");
        System.out.println("Output: " + solution.containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3)); // Expected: true

        System.out.println("\nTest Case 2:");
        System.out.println("Input: nums = [1,0,1,1], k = 1");
        System.out.println("Output: " + solution.containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1)); // Expected: true

        System.out.println("\nTest Case 3:");
        System.out.println("Input: nums = [1,2,3,1,2,3], k = 2");
        System.out.println("Output: " + solution.containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2)); // Expected: false

        System.out.println("\nTest Case 4:");
        System.out.println("Input: nums = [99,99], k = 2");
        System.out.println("Output: " + solution.containsNearbyDuplicate(new int[]{99, 99}, 2)); // Expected: true
    }
}
