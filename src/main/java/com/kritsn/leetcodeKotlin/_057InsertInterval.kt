package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 18, 2025
///////////////////////////////////////////////////////////////////////////
/**
 * You are given an array of non-overlapping intervals sorted by start time.
 * Insert a new interval such that the result remains sorted and non-overlapping.
 * Merge intervals if necessary.
 */
class _057InsertInterval {

    ///////////////////////////////////////////////////////////////////////////
    // https://www.youtube.com/watch?v=xxRE-46OCC8
    // Insert & Merge Interval Approach:
    //
    // 🪜 Steps:
    // 1. Add all intervals ending before newInterval starts.
    // 2. Merge all overlapping intervals with newInterval.
    // 3. Add all remaining intervals.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — we iterate through all intervals once.
    // Space Complexity: O(n) — for storing the resulting intervals.
    ///////////////////////////////////////////////////////////////////////////
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        var i = 0
        val n = intervals.size

        // Step 1: Add intervals that end before newInterval starts
        //it will keep adding all interval till new interval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i])
            i++
        }

        // Step 2: Merge all overlapping intervals with newInterval
        var start = newInterval[0]
        var end = newInterval[1]
        while (i < n && intervals[i][0] <= end) {
            start = minOf(start, intervals[i][0])
            end = maxOf(end, intervals[i][1])
            i++
        }
        result.add(intArrayOf(start, end))  // merged newInterval

        // Step 3: Add all remaining intervals
        while (i < n) {
            result.add(intervals[i])
            i++
        }

        return result.toTypedArray()
    }

    ///////////////////////////////////////////////////////////////////////////
    // 🔍 Main method with clearly labeled test cases
    ///////////////////////////////////////////////////////////////////////////
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _057InsertInterval()

            val intervals1 = arrayOf(intArrayOf(1,3), intArrayOf(6,9))
            val newInterval1 = intArrayOf(2,5)
            println("Test Case 1: ${solver.insert(intervals1, newInterval1).joinToString { it.joinToString(",", "[", "]") }}")
            // Expected: [[1,5],[6,9]]

            val intervals2 = arrayOf(intArrayOf(1,2), intArrayOf(3,5), intArrayOf(6,7), intArrayOf(8,10), intArrayOf(12,16))
            val newInterval2 = intArrayOf(4,8)
            println("Test Case 2: ${solver.insert(intervals2, newInterval2).joinToString { it.joinToString(",", "[", "]") }}")
            // Expected: [[1,2],[3,10],[12,16]]

            val intervals3 = arrayOf<IntArray>()
            val newInterval3 = intArrayOf(5,7)
            println("Test Case 3: ${solver.insert(intervals3, newInterval3).joinToString { it.joinToString(",", "[", "]") }}")
            // Expected: [[5,7]]

            val intervals4 = arrayOf(intArrayOf(1,5))
            val newInterval4 = intArrayOf(2,3)
            println("Test Case 4: ${solver.insert(intervals4, newInterval4).joinToString { it.joinToString(",", "[", "]") }}")
            // Expected: [[1,5]]

            val intervals5 = arrayOf(intArrayOf(1,5))
            val newInterval5 = intArrayOf(6,8)
            println("Test Case 5: ${solver.insert(intervals5, newInterval5).joinToString { it.joinToString(",", "[", "]") }}")
            // Expected: [[1,5],[6,8]]
        }
    }
}

