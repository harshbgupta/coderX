package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 18, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * 📄 Problem:
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals.
 * Return an array of the non-overlapping intervals that cover all the intervals in the input.
 */
class _056MergeIntervals {

    ///////////////////////////////////////////////////////////////////////////
    // Greedy + Sorting Approach:
    //
    // Sort all intervals based on their start time.
    // Then iterate through each interval and merge it with the last one in the result list
    // if it overlaps, otherwise add it to the result.
    //
    // 🪜 Steps:
    // 1. Sort the intervals by start time.
    // 2. Initialize an empty result list.
    // 3. For each interval:
    //      - If it overlaps with the last interval in result, merge them.
    //      - Otherwise, add it to result as is.
    //
    // ⏱ Time & Space Complexity:
    // Time Complexity: O(n log n) for sorting + O(n) for merging => O(n log n)
    // Space Complexity: O(n) for output list
    ///////////////////////////////////////////////////////////////////////////
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        // 🧠 Edge case: if input is empty or has only one interval, return as is
        if (intervals.size <= 1) return intervals

        // ✅ Step 1: Sort intervals by starting time
        intervals.sortBy { it[0] }

        val merged = mutableListOf<IntArray>()

        for (interval in intervals) {
            // 🔍 Get start and end of current interval
            val (start, end) = interval

            // 📌 If result list is empty or no overlap, simply add interval
            if (merged.isEmpty() || merged.last()[1] < start) {
                merged.add(intArrayOf(start, end))
            } else {
                // 🔄 Overlap detected: Merge by updating end of last interval
                merged.last()[1] = maxOf(merged.last()[1], end)
            }
        }

        return merged.toTypedArray()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _056MergeIntervals()

            val test1 = arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))
            println("Input: [[1,3],[2,6],[8,10],[15,18]]")
            println("Output: " + solution.merge(test1).joinToString { "[${it[0]},${it[1]}]" })

            val test2 = arrayOf(intArrayOf(1, 4), intArrayOf(4, 5))
            println("\nInput: [[1,4],[4,5]]")
            println("Output: " + solution.merge(test2).joinToString { "[${it[0]},${it[1]}]" })

            val test3 = arrayOf(intArrayOf(1, 4), intArrayOf(5, 6))
            println("\nInput: [[1,4],[5,6]]")
            println("Output: " + solution.merge(test3).joinToString { "[${it[0]},${it[1]}]" })

            val test4 = arrayOf(intArrayOf(1, 10), intArrayOf(2, 3), intArrayOf(4, 5))
            println("\nInput: [[1,10],[2,3],[4,5]]")
            println("Output: " + solution.merge(test4).joinToString { "[${it[0]},${it[1]}]" })
        }
    }
}
