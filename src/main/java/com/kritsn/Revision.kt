package com.kritsn

import com.kritsn.utils.Bag

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since July 04, 2025
 */

fun main() {
    var nums1 = intArrayOf(2, 2, 3, 4, 0)
    var nums2 = intArrayOf(1)
    Bag.printArray(mergeSortedArrayOptimised(nums1, 4, nums2, nums2.size))
}

fun mergeSortedArrayOptimised(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {
    var p1 = m - 1
    var p2 = n - 1

    var i = m + n - 1

    while (p1 >= 0 && p2 >= 0) {
        if (nums1[p1] > nums2[p2]) {
            nums1[i] = nums1[p1]
            p1--
        } else {
            nums1[i] = nums2[p2]
            p2--
        }
        i--
    }

    while (p2 >= 0) {
        nums1[i] = nums2[p2]
        p2--
        i--
    }

    return nums1
}