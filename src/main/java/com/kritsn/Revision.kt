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

fun mergeSortedArrayBruteForce(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {
//    val n = nums1.size
//    val m = nums1.size
    var sol = IntArray(m + n)
    var index = 0

    var i = 0
    var j = 0

    while (i + j < m + n) {
        if (i >= m || j >= n) {
            while (i < m) {
                val element = nums1[i]
                sol[index] = element
                index++ //increasing index of Sol
                i++ //increasing index for nums 1
            }

            while (j < n) {
                val element = nums2[j]
                sol[index] = element
                index++ //increasing index of Sol
                j++ //increasing index for nums 1
            }
        } else {
            val element1 = nums1[i]
            val element2 = nums2[j]

            when {
                element1 < element2 -> {
                    sol[index] = element1
                    index++ //increasing index of Sol
                    i++ //increasing index for nums 1
                }

                element1 > element2 -> {
                    sol[index] = element2
                    index++ //increasing index of Sol
                    j++ //increasing index for nums 2
                }

                else -> {
                    //element1 == element2 condition
                    sol[index] = element1
                    index++ //increasing index of Sol
                    i++ //increasing index for nums 1
                    sol[index] = element2
                    index++ //increasing index of Sol
                    j++ //increasing index for nums 1
                }
            }
        }
    }
    for ((index, ele) in sol.withIndex()) {
        nums1[index] = ele
    }
    return nums1
}


fun mergeSortedArrayOptimised(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {
    var i = m - 1
    var j = n - 1
    var k = m + n - 1

    while (i >= 0 && j >= 0) {
        if (nums1[i] > nums2[j]) {
            nums1[k] = nums1[i]
            i--
        } else {
            nums1[k] = nums2[j]
            j--
        }
        k--
    }

    //we are checking only 2nd array coz first array is already sorted,
    // and 2nd array are done then we do not require to following on first array
    while (j >= 0) {
        nums1[k] = nums2[j]
        j--
        k--
    }
    return nums1
}