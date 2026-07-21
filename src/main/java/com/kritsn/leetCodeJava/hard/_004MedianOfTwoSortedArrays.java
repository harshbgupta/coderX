package com.kritsn.leetCodeJava.hard;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * LeetCode Question4: Median of Two Sorted Arrays
 * <p>
 * Given two sorted arrays nums1 and nums2 of size m and n respectively,
 * this function returns the median of the two sorted arrays.
 * The overall run time complexity is O(log(min(m, n))).
 */

/**
 * Solution and Explanation: The brute-force approach would be to merge the two arrays into one,
 * which takes O(m+n) time, and then find the median. However, the requirement is O(log(m+n)),
 * which strongly suggests a binary search approach. The key insight is not to search for a value,
 * but to search for the correct partition in the arrays. We want to split the combined (conceptual)
 * array into two halves, a "left part" and a "right part", such that:
 * 1. The number of elements in the left part is equal to (or one more than) the number of elements
 * in the right part.
 * 2. Every element in the left part is less than or equal to every element in the right part.
 * If we can find this perfect partition, the median can be found from the maximum element in the
 * left part and the minimum element in the right part. We can find this partition using binary search.
 */
public class _004MedianOfTwoSortedArrays {

    /**
     * Finds the median of two sorted arrays.
     * <p>
     * This solution uses a binary search approach on the smaller of the two arrays
     * to find the correct partition point. The partition divides the combined
     * elements into two halves: a "left part" and a "right part".
     * <p>
     * The median is then determined by the boundary elements of this partition:
     * - If the total number of elements is odd, the median is the maximum element in the left part.
     * - If the total number of elements is even, the median is the average of the maximum
     * element in the left part and the minimum element in the right part.
     *
     * @param nums1 The first sorted integer array.
     * @param nums2 The second sorted integer array.
     * @return The median of the two arrays as a double.
     */
    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // To ensure the binary search is on the smaller array for efficiency,
        // we swap the arrays if nums1 is larger than nums2.
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int low = 0, high = m; // The search space for the partition is from 0 to m.
        while (low <= high) {
            // partitionX is the split point in the first array (nums1).
            // It represents how many elements from nums1 are in the "left part".
            int partitionX = (low + high) / 2;
            // partitionY is calculated to ensure the total number of elements
            // in the combined left part is half of the total elements.
            // The `+1` handles both even and odd total lengths gracefully.
            int partitionY = (m + n + 1) / 2 - partitionX;

            // Get the boundary elements for the partitions.
            // If a partition is at an edge (0 or size), we use MIN/MAX values
            // to avoid out-of-bounds errors and to ensure comparisons work correctly.

            // l1 is the largest element on the left side of the partition in nums1.
            int l1 = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            // l2 is the largest element on the left side of the partition in nums2.
            int l2 = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];

            // r1 is the smallest element on the right side of the partition in nums1.
            int r1 = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            // r2 is the smallest element on the right side of the partition in nums2.
            int r2 = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            // Check if we have found the correct partition.
            // This is true if the max of the left part is <= the min of the right part.
            if (l1 <= r2 && l2 <= r1) {
                // We've found the perfect partition, now calculate the median.
                if ((m + n) % 2 == 0) { //even length
                    // If the total length is even, the median is the average of the
                    // two middle elements: max of the left parts and min of the right parts.
                    return (Math.max(l1, l2) + (double) Math.min(r1, r2)) / 2;
                } else { //odd length
                    // If the total length is odd, the median is the single middle element,
                    // which is the maximum of the left parts.
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                // The partition in nums1 is too far to the right.
                // We need to move the partition to the left.
                high = partitionX - 1;
            } else {
                // The partition in nums1 is too far to the left.
                // We need to move the partition to the right.
                low = partitionX + 1;
            }
        }

        // This part should not be reached if the inputs are sorted arrays.
        // It's included to handle unexpected cases and make the compiler happy.
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 7, 10, 12};
        int[] nums2 = {2, 3, 6, 15};
        System.out.println("Median of " + Arrays.toString(nums1) + " & " + Arrays.toString(nums2)
                + " is " + new _004MedianOfTwoSortedArrays().findMedianSortedArrays(nums1, nums2));
    }
}
