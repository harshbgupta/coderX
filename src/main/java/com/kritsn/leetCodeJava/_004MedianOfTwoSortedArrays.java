package com.kritsn.leetCodeJava;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 04, 2025
 */

public class _004MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        _004MedianOfTwoSortedArrays obj = new _004MedianOfTwoSortedArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(obj.findMediumOfTwoSortedArrays(nums1, nums2)); // Output: 2.0

    }

    private double findMediumOfTwoSortedArrays(int[] nums1, int[] nums2) {
        // To ensure the binary search is on the smaller array for efficiency,
        // we swap the arrays if nums1 is larger than nums2.
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMediumOfTwoSortedArrays(nums2, nums1);
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
            int l2 = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            // r1 is the smallest element on the right side of the partition in nums1.
            int r1 = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            // r2 is the smallest element on the right side of the partition in nums2.
            int r2 = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            // Check if we have found the correct partition.
            // This is true if the max of the left part is <= the min of the right part.
            if (l1 <= r2 && r1 <= l2) {
                // We've found the perfect partition, now calculate the median.
                if ((m + n) % 2 == 0) { //even length
                    // If the total length is even, the median is the average of the
                    // two middle elements: max of the left parts and min of the right parts.
                    return ((double) Math.max(l1, r1) + Math.min(l2, r2)) / 2;
                } else { //odd length
                    // If the total length is odd, the median is the single middle element,
                    // which is the maximum of the left parts.
                    return (double) Math.max(l1, r1);
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
        throw new IllegalArgumentException();
    }

}
