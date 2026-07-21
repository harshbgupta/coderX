package com.kritsn.leetCodeJava.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/*
Problem:
You are given a sorted unique integer array nums.
Return the smallest list of ranges covering all numbers in the array exactly.
Each range [a, b] should be formatted as:
- "a->b" if a != b
- "a" if a == b
*/
public class _228SummaryRanges {

    /**
     * 🧠 Algorithm & Approach:
     * --------------------------------------------
     * 1. Traverse through the array and find segments where consecutive numbers exist.
     * 2. Maintain a start pointer for the beginning of a new range.
     * 3. Whenever the current number is NOT consecutive, close the previous range.
     * 4. Add the formatted range string to the result list.
     * 5. After loop ends, make sure to add the last pending range.
     * <p>
     * ✅ Time Complexity: O(n) — Single pass through the array.
     * ✅ Space Complexity: O(1) extra (excluding result list).
     */
    List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        // Edge case: empty input
        if (nums.length == 0) return result;

        // Start of the current range
        int start = nums[0];

        // Iterate through 1..n to compare with previous element
        for (int i = 1; i < nums.length; i++) {
            // If current is not consecutive to previous
            if (nums[i] != nums[i - 1] + 1) {
                // Add the completed range to result
                if (start == nums[i - 1]) {
                    result.add(String.valueOf(start));         // Single element range
                } else {
                    result.add(start + "->" + nums[i - 1]); // Multiple elements
                }
                // Start a new range
                start = nums[i];
            }
        }

        // Add the last range (not handled in loop)
        int last = nums[nums.length - 1];
        if (start == last) {
            result.add(String.valueOf(start));
        } else {
            result.add(start + "->" + last);
        }

        return result;
    }

    public static void main(String[] args) {
        _228SummaryRanges obj = new _228SummaryRanges();

        int[] nums1 = {0, 1, 2, 4, 5, 7};
        System.out.println("Test 1: Input = " + joined(nums1));
        System.out.println("Output = " + obj.summaryRanges(nums1));
        System.out.println("Expected = [\"0->2\", \"4->5\", \"7\"]\n");

        int[] nums2 = {0, 2, 3, 4, 6, 8, 9};
        System.out.println("Test 2: Input = " + joined(nums2));
        System.out.println("Output = " + obj.summaryRanges(nums2));
        System.out.println("Expected = [\"0\", \"2->4\", \"6\", \"8->9\"]\n");

        int[] nums3 = {1};
        System.out.println("Test 3: Input = " + joined(nums3));
        System.out.println("Output = " + obj.summaryRanges(nums3));
        System.out.println("Expected = [\"1\"]\n");

        int[] nums4 = {};
        System.out.println("Test 4: Input = " + joined(nums4));
        System.out.println("Output = " + obj.summaryRanges(nums4));
        System.out.println("Expected = []\n");

        int[] nums5 = {1, 3, 5, 7};
        System.out.println("Test 5: Input = " + joined(nums5));
        System.out.println("Output = " + obj.summaryRanges(nums5));
        System.out.println("Expected = [\"1\", \"3\", \"5\", \"7\"]");
    }

    private static String joined(int[] nums) {
        return IntStream.of(nums).mapToObj(String::valueOf).collect(Collectors.joining(", "));
    }
}
