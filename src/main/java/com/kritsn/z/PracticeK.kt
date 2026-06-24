package com.kritsn.z

import com.kritsn.leetcodeKotlin._004MedianOfTwoSortedArrays

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 * 
 * @author Radhey (hr-sh)
 * @since Jun 07, 2026
 */

fun main() {
    // Test Case 1: Remove 3 from the array
    val nums1 = intArrayOf(3, 2, 2, 3)
    val val1 = 3
    val result1 = removeDuplicates(nums1)
    println("Test Case 1: Input = ${nums1.contentToString()}, Result k = $result1, Modified nums = ${nums1.sliceArray(0 until result1).contentToString()}") // Expected k = 2, Modified nums = [2, 2]

    // Test Case 2: Remove 2 from the array
    val nums2 = intArrayOf(0, 1, 2, 2, 3, 0, 4, 2)
    val val2 = 2
    val result2 = removeDuplicates(nums2)
    println("Test Case 2: Input = ${nums2.contentToString()}, Result k = $result2, Modified nums = ${nums2.sliceArray(0 until result2).contentToString()}") // Expected k = 5, Modified nums = [0, 1, 3, 0, 4]

    // Test Case 3: No element to remove
    val nums3 = intArrayOf(1, 2, 3)
    val val3 = 5
    val result3 = removeDuplicates(nums3)
    println("Test Case 3: Input = ${nums3.contentToString()}, Result k = $result3, Modified nums = ${nums3.sliceArray(0 until result3).contentToString()}") // Expected k = 3, Modified nums = [1, 2, 3]

}

fun removeDuplicates(arr: IntArray): Int{
    var  pointerSlow = 1
    for (pointerFast in 1..<arr.size){
        if(arr[pointerFast] != arr[pointerFast-1]){
            arr[pointerSlow] = arr[pointerFast]
            pointerSlow++
        }
    }
    return pointerSlow
}
