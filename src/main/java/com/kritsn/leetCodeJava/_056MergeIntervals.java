package com.kritsn.leetCodeJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 📄 Problem:
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals.
 * Return an array of the non-overlapping intervals that cover all the intervals in the input.
 */
public class _056MergeIntervals {

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
    int[][] merge(int[][] intervals) {
        // 🧠 Edge case: if input is empty or has only one interval, return as is
        if (intervals.length <= 1) return intervals;

        // ✅ Step 1: Sort intervals by starting time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            // 🔍 Get start and end of current interval
            int start = interval[0];
            int end = interval[1];

            // 📌 If result list is empty or no overlap, simply add interval
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < start) {
                merged.add(new int[]{start, end});
            } else {
                // 🔄 Overlap detected: Merge by updating end of last interval
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], end);
            }
        }

        return merged.toArray(new int[0][]);
    }

    private static String toString(int[][] intervals) {
        return Arrays.stream(intervals)
                .map(it -> "[" + it[0] + "," + it[1] + "]")
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        _056MergeIntervals solution = new _056MergeIntervals();

        int[][] test1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Input: [[1,3],[2,6],[8,10],[15,18]]");
        System.out.println("Output: " + toString(solution.merge(test1)));

        int[][] test2 = {{1, 4}, {4, 5}};
        System.out.println("\nInput: [[1,4],[4,5]]");
        System.out.println("Output: " + toString(solution.merge(test2)));

        int[][] test3 = {{1, 4}, {5, 6}};
        System.out.println("\nInput: [[1,4],[5,6]]");
        System.out.println("Output: " + toString(solution.merge(test3)));

        int[][] test4 = {{1, 10}, {2, 3}, {4, 5}};
        System.out.println("\nInput: [[1,10],[2,3],[4,5]]");
        System.out.println("Output: " + toString(solution.merge(test4)));
    }
}
