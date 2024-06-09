package com.vertical.leetcode.topIvsQues.arraysAndStrings

import logger
import toJsonString

fun main(args: Array<String>) {
    toJsonString(searchSolMain2(intArrayOf(8, 9, 10, 11, 12, 15, 0, 1), 15))
}

/**
 * Search in Rotated Sorted Array
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
 * or -1 if it is not in nums. You must write an algorithm with O(log n) runtime complexity.
 */
private fun searchSolMain(nums: IntArray, target: Int): Int {
    //this is given O(logn) runtime, so it's most probably binary search
    /*
        Condition 1:
     */
    logger("Array ${toJsonString(nums)}, target $target")
    return binarySearch(nums, target, 0, nums.size - 1)
}

/**
 * Solution number 1
 */
private fun binarySearchOnRotatedSortedArray(array: IntArray, targetEle: Int, leftIndex: Int, rightIndex: Int): Int {
    val mid = (rightIndex + leftIndex) / 2
    logger("=================================================")
    logger("leftIndex $leftIndex, rightIndex $rightIndex")
    logger("leftValue ${array[leftIndex]}, rightValue ${array[rightIndex]}")
    logger("midIndex $mid,midValue: ${array[mid]}, target $targetEle} condition ${array[mid] == targetEle}")
    var ans = -1
    if (array[mid] == targetEle) {
        println("match found mid")
        ans = mid
    } else {
        if (array[leftIndex] <= array[rightIndex]) { //array is not pivoted
            println("array is not pivoted")
            binarySearch(array, targetEle, leftIndex, rightIndex)
        } else {//array is pivoted
            println("array is pivoted")
            if (mid != 0 && array[mid - 1] > array[mid]) { //Condition a: mid-index is pivot Index, so put binary search both side
                when {
                    targetEle > array[mid - 1] -> return -1;
                    targetEle <= array[mid - 1] && targetEle >= array[leftIndex] -> {
                        binarySearchOnRotatedSortedArray(array, targetEle, leftIndex, mid - 1)
                    }

                    targetEle >= array[mid + 1] && targetEle <= array[rightIndex] -> {
                        binarySearchOnRotatedSortedArray(array, targetEle, mid + 1, rightIndex)
                    }
                }
            } else {
                when {
                    targetEle > array[mid] -> {
                        binarySearchOnRotatedSortedArray(array, targetEle, mid + 1, rightIndex)
                    }

                    targetEle < array[mid] -> {
                        binarySearchOnRotatedSortedArray(array, targetEle, leftIndex, mid - 1)
                    }
                }
            }
        }
    }
    return ans

}

/**
 * Solution number 2
 */
private fun searchSolMain2(nums: IntArray, target: Int): Int {
    logger("Array ${toJsonString(nums)}, target $target")
    val pivotIndex = findPivotIndex(nums)
    val arraySorted = nums.sortedArray()
    val targetEleIndexInSortedArray = binarySearch(arraySorted, target, 0, nums.size - 1)
    logger("Array Sorted ${toJsonString(targetEleIndexInSortedArray)}, pivotIndex $pivotIndex")
    logger("targetEleIndexInSortedArray + pivotIndex ${targetEleIndexInSortedArray + pivotIndex}")
    return if ((targetEleIndexInSortedArray + pivotIndex) > nums.size) {
        targetEleIndexInSortedArray + pivotIndex - nums.size
    } else {
        targetEleIndexInSortedArray + pivotIndex
    }
}

private fun findPivotIndex(array: IntArray): Int {
    if (array[0] <= array[array.size - 1]) {//array is not pivoted at all
        return 0
    } else {
        for ((index, ele) in array.withIndex()) {
            if (index > 0 && index < array.size && array[index - 1] > array[index]) {
                return index
            }
        }
    }
    return 0
}

private fun binarySearch(array: IntArray, targetEle: Int, left: Int, right: Int): Int {
    while (left <= right) {
        val mid = (left + right) / 2
        when {
            targetEle > array[mid] -> return binarySearch(
                array,
                targetEle,
                mid + 1,
                right
            )   // element is greater than middle element of array, so it will be in right half. Recursion will call the right half again
            targetEle < array[mid] -> return binarySearch(
                array,
                targetEle,
                left,
                mid - 1
            )    //element is less than middle element of array, so it will be in left half of the array. Recursion will call the left half again.
            targetEle == array[mid] -> return mid // element found.
        }
    }
    return -1
}

/**
 * Solution number 3
 */
private fun searchSolInbuiltFun(nums: IntArray, target: Int): Int {
    return nums.indexOf(target)
}