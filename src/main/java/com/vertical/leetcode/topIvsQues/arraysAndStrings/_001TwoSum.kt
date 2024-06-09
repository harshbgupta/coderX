package com.vertical.leetcode.topIvsQues.arraysAndStrings

import toJsonString


fun main(args: Array<String>) {
    println(
        "Result: " +
                toJsonString(twoSum(intArrayOf(3, 3), 6))
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