package com.kritsn.leetcode

import toJsonString


fun main(args: Array<String>) {
    println(
        "Result: " +
                toJsonString(twoSum(intArrayOf(3, 3), 6))
    )
    // Example 1
    println(
        "Result for [2, 7, 11, 15], target 9: " +
                toJsonString(twoSumOptimized(intArrayOf(2, 7, 11, 15), 9))
    )

    // Example 2 (the edge case from your original code)
    println(
        "Result for [3, 3], target 6: " +
                toJsonString(twoSumOptimized(intArrayOf(3, 3), 6))
    )

    // Example 3
    println(
        "Result for [3, 2, 4], target 6: " +
                toJsonString(twoSumOptimized(intArrayOf(3, 2, 4), 6))
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
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * This is an optimized one-pass hash map solution.
 *
 * Time Complexity: O(n) - We iterate through the array only once. Each map lookup is O(1) on average.
 * Space Complexity: O(n) - In the worst case, we store all n elements in the hash map.
 */
private fun twoSumOptimized(nums: IntArray, target: Int): IntArray {
    // A map to store the number and its index: {number -> index}
    val numMap = HashMap<Int, Int>()

    // Iterate through the array with both the value and its index
    nums.forEachIndexed { index, num ->
        val complement = target - num
        // Check if the complement needed to reach the target exists in our map
        if (numMap.containsKey(complement)) {
            // If it exists, we found our solution.
            // The '!!' (non-null assertion) is safe because we just checked for the key's existence.
            return intArrayOf(numMap[complement]!!, index)
        }
        // If the complement is not found, add the current number and its index to the map
        // for subsequent iterations to check against.
        numMap[num] = index
    }

    // Per the problem statement, a solution always exists.
    // Throwing an exception is a robust way to handle cases where no solution is found,
    // which would violate the problem's contract.
    throw IllegalArgumentException("No two sum solution found")
}