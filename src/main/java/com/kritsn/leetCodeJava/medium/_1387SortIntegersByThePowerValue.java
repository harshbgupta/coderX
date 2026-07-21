package com.kritsn.leetCodeJava.medium;
import java.util.*;

/**
 * LeetCode 1387 - Sort Integers by The Power Value
 */
public class _1387SortIntegersByThePowerValue {

    // Memoization map
    private final Map<Integer, Integer> memo = new HashMap<>();

    public _1387SortIntegersByThePowerValue() {
        // Base case
        memo.put(1, 0);
    }

    /**
     * Computes the power value of a number using
     * recursion + memoization.
     *
     * Time Complexity:
     * Nearly O(1) amortized per repeated state.
     */
    private int power(int x) {

        // Already computed
        if (memo.containsKey(x))
            return memo.get(x);

        int steps;

        // Even number
        if (x % 2 == 0) {
            steps = 1 + power(x / 2);
        }
        // Odd number
        else {
            steps = 1 + power(3 * x + 1);
        }

        memo.put(x, steps);

        return steps;
    }

    /**
     * Algorithm
     * ----------------------------
     * 1. Compute power for every number.
     * 2. Store pair(number, power).
     * 3. Sort by power.
     * 4. If equal, sort by number.
     * 5. Return kth element.
     *
     * Time Complexity:
     * O(n log n)
     *
     * Space Complexity:
     * O(n)
     */
    public int getKth(int lo, int hi, int k) {

        List<int[]> list = new ArrayList<>();

        for (int i = lo; i <= hi; i++) {
            list.add(new int[]{i, power(i)});
        }

        list.sort((a, b) -> {

            if (a[1] != b[1])
                return a[1] - b[1];

            return a[0] - b[0];
        });

        return list.get(k - 1)[0];
    }

    public static void main(String[] args) {

        _1387SortIntegersByThePowerValue solution =
                new _1387SortIntegersByThePowerValue();

        System.out.println(solution.getKth(12, 15, 2)); //13
        System.out.println(solution.getKth(1, 1, 1));   //1
        System.out.println(solution.getKth(7, 11, 4));  //7
    }
}