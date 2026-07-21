package com.kritsn.leetCodeJava.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 📄 Problem Statement:
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented
 * as a 2D integer array `points` where `points[i] = [xstart, xend]` denotes a balloon whose horizontal diameter stretches
 * between xstart and xend. You do not know the exact y-coordinates of the balloons.
 * <p>
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart &lt;= x &lt;= xend. There is no limit to the number
 * of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 * <p>
 * Given the array `points`, return the minimum number of arrows that must be shot to burst all balloons.
 */
public class _452MinimumArrowsToBurstBalloons {

    /**
     * https://www.youtube.com/watch?v=Z9o-lqwgSWA
     * 🧠 Algorithm & Approach:
     * - Sort the intervals by their ending x-coordinate.
     * - Place the first arrow at the end of the first balloon.
     * - For each balloon:
     * - If it starts after the last arrow position, shoot a new arrow and update the position.
     * - Else, it is already burst by the existing arrow.
     * <p>
     * Time Complexity: O(n log n) - due to sorting
     * Space Complexity: O(1) - in-place sorting and constant extra space
     */
    int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;

        // Sort balloons by their end point
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));

        int arrows = 1;
        int endPoint = points[0][1]; // place the first arrow at the end of the first balloon

        for (int i = 1; i < points.length; i++) {
            // If current balloon starts after the last arrow position, it needs a new arrow
            if (points[i][0] > endPoint) {
                arrows++;
                endPoint = points[i][1];
            }
            // else, this balloon is already burst by the previous arrow
        }

        return arrows;
    }

    public static void main(String[] args) {
        _452MinimumArrowsToBurstBalloons solver = new _452MinimumArrowsToBurstBalloons();

        int[][] test1 = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println("Test 1 Input: " + Arrays.deepToString(test1));
        System.out.println("Minimum arrows needed: " + solver.findMinArrowShots(test1)); // Expected: 2

        int[][] test2 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        System.out.println("\nTest 2 Input: " + Arrays.deepToString(test2));
        System.out.println("Minimum arrows needed: " + solver.findMinArrowShots(test2)); // Expected: 4

        int[][] test3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println("\nTest 3 Input: " + Arrays.deepToString(test3));
        System.out.println("Minimum arrows needed: " + solver.findMinArrowShots(test3)); // Expected: 2
    }
}
