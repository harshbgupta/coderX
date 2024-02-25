package com.vertical.leetcode

import logger
import toJsonString

fun main() {
    println(
        "Result: " +
//                toJsonString(twoSum(intArrayOf(3, 3), 6))
//                toJsonString(lengthOfLongestSubstring("pwwkew"))
//                toJsonString(validBracesSol2("({{{{}}}))"))
//                toJsonString(maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)))
                toJsonString(searchSolMain2(intArrayOf(8, 9, 10, 11, 12, 15, 0, 1), 15))
    )
}

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 */
private fun twoSum(nums: IntArray, target: Int): IntArray? {
    val intArray = IntArray(2)
    //for (i in 0 until nums.size - 1) {
    // this will not work assume the edge case [3,3],target is 6
    //because for i =0 if we check diff index it will return 0  and if put the condition index != i, so in this case,
    //code will fail, So we will run the loop from end that will solve the issue first iteration on i =1 the diff
    // index will 0 which will work and resolve the case

    for (i in nums.size - 1 downTo 0) {//this will work assume the edge case [3,3],target is 6
        val diff = target - nums[i]
        val index = nums.indexOf(diff)
        println("i and index $i , $index")
        if (index != -1 && index != i) {
            println("i and index after $i , $index")
            intArray[0] = i
            intArray[1] = index
            return intArray
        }
    }
    return null
}

/**
 * Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 */
private fun lengthOfLongestSubstring(s: String): Int {
    var ans = ""
    var tempAns = ""
    s.forEachIndexed { index, letter ->
        val duplicateCharIndex = tempAns.indexOf(letter)
        if (duplicateCharIndex == -1) {
            tempAns += letter
            if (tempAns.length > ans.length) {
                ans = tempAns
            }
        } else {
            tempAns = tempAns.substring(duplicateCharIndex + 1, tempAns.length) + letter
        }
    }
//    println("ans $ans")
    return ans.length
}

/**
 * Valid Parentheses
 *
 * Given a strings containing just the characters '('`,')'`,'{'`,'}'`,'['and']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 */
private fun validBracesSol1(s: String): Boolean {
    var i = -1
    val stack = CharArray(s.length)
    for (ch in s.toCharArray()) {
        if (ch == '(' || ch == '{' || ch == '[') {
            stack[++i] = ch
        } else {
            if (i >= 0 &&
                ((ch == ')' && stack[i] == '(')
                        || (ch == '}' && stack[i] == '{')
                        || (ch == ']' && stack[i] == '['))
            ) {
                i--
            } else {
                return false
            }
        }
    }
    return i == -1
}

private fun validBracesSol2(s: String): Boolean {
    var tempString = ""
    var i = -1;
    for (ch in s.toCharArray()) {
        if (ch == '(' || ch == '{' || ch == '[') {
            tempString += ch
            i++
        } else {
            if (i >= 0 &&
                ((ch == ')' && tempString[i] == '(')
                        || (ch == '}' && tempString[i] == '{')
                        || (ch == ']' && tempString[i] == '['))
            ) {
                tempString = tempString.replace(tempString[i].toString(), "")
                i--
            } else {
                return false
            }
        }
    }
    return tempString.isEmpty()
}

/**
 * Best Time to Buy and Sell Stock
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
private fun maxProfit(prices: IntArray): Int {
    var maxProfit = 0
    var investPrice = prices[0]
//    for ((index:, price)  in prices.withIndex()) {
    for (i in 1 until prices.size) {
        val price = prices[i]
        if (investPrice > price) {
            investPrice = price
        } else if ((price - investPrice) > maxProfit) {
            maxProfit = price - investPrice
        }
//        logger("index: $i", "price: $price", "investPrice: $investPrice", "maxProfit: $maxProfit")
    }
    return maxProfit
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
/*
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

private fun searchSolInbuiltFun(nums: IntArray, target: Int): Int {
    return nums.indexOf(target)
}