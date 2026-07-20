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
 * You are given an array of non-overlapping intervals sorted by start time.
 * Insert a new interval such that the result remains sorted and non-overlapping.
 * Merge intervals if necessary.
 */
public class _057InsertInterval {

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
    int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Step 1: Add intervals that end before newInterval starts
        // it will keep adding all interval till new interval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Step 2: Merge all overlapping intervals with newInterval
        int start = newInterval[0];
        int end = newInterval[1];
        while (i < n && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        result.add(new int[]{start, end}); // merged newInterval

        // Step 3: Add all remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[0][]);
    }

    private static String toString(int[][] intervals) {
        return Arrays.stream(intervals)
                .map(it -> "[" + it[0] + "," + it[1] + "]")
                .collect(Collectors.joining(","));
    }

    ///////////////////////////////////////////////////////////////////////////
    // 🔍 Main method with clearly labeled test cases
    ///////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        _057InsertInterval solver = new _057InsertInterval();

        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        System.out.println("Test Case 1: " + toString(solver.insert(intervals1, newInterval1)));
        // Expected: [[1,5],[6,9]]

        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        System.out.println("Test Case 2: " + toString(solver.insert(intervals2, newInterval2)));
        // Expected: [[1,2],[3,10],[12,16]]

        int[][] intervals3 = {};
        int[] newInterval3 = {5, 7};
        System.out.println("Test Case 3: " + toString(solver.insert(intervals3, newInterval3)));
        // Expected: [[5,7]]

        int[][] intervals4 = {{1, 5}};
        int[] newInterval4 = {2, 3};
        System.out.println("Test Case 4: " + toString(solver.insert(intervals4, newInterval4)));
        // Expected: [[1,5]]

        int[][] intervals5 = {{1, 5}};
        int[] newInterval5 = {6, 8};
        System.out.println("Test Case 5: " + toString(solver.insert(intervals5, newInterval5)));
        // Expected: [[1,5],[6,8]]
    }
}
