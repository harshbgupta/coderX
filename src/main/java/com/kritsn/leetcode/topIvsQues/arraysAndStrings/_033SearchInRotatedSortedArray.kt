package com.kritsn.leetcode.topIvsQues.arraysAndStrings

import toJsonString
/** NOTE:::
 * 1. if you see search and sorted go for Binary search
 * 2. If Time complexity is O(logN) then it's very high chance the ans will be done by Binary search
 */

/**
 * Search in Rotated Sorted Array
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
 * or -1 if it is not in nums. You must write an algorithm with O(log n) runtime complexity.
 */
fun main(args: Array<String>) {
    toJsonString(solutionWithLogNTimeComplexity(intArrayOf(8, 9, 10, 11, 12, 15, 0, 1), 15))
}


//here we can use leaner search (just out a for loop and search for ele) but in this case time complexity will be O(N) But
//here we see the  `search` and `sorted` go for BINARY search always
//Interview will ask for O(logN) time complexity
fun solutionWithLogNTimeComplexity(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    while (left <= right) {
        val mid = (left + right) / 2
        if (nums[mid] == target) {
            return mid
        }

        //figuring out the which side is sorted in `Rotated Sorted Array`
        if (nums[left] <= nums[mid]) { //left sorted array
            if (target > nums[mid]) {//target falls in RIGHT side of mid
                left = mid + 1
            } else if (target < nums[left]) { //target falls in RIGHT side of mid again
                left = mid + 1
            } else { //target falls in LEFT side of mid now
                right = mid - 1
            }
        } else { //right sorted array
            if (target < nums[mid]) { //target falls in LEFT side of mid
                right = mid - 1
            } else if (target > nums[right]) { //target falls in LEFT side of mid again
                right = mid - 1
            } else { //target falls in RIGHT side of mid
                left = mid + 1
            }
        }
    }
    return -1
}
