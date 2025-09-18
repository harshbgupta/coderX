package com.kritsn.ques;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 17, 2025
 */

public class Fib {

    public static void main(String[] args) {
        printFibSeries(5);
    }

    /**
     *
     *
     * TC:
     * SC:
     */
    private static void printFibSeries(int n) {
        int a = 0, b = 1;
        System.out.print(a + " "); // print first
        if (n > 0) {
            System.out.print(b + " "); // print second
        }

        for (int i = 2; i <= n; i++) {
            int c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }
    }
    private static int findNthEleInFib(int n) {
        if (n <= 1) {
            return n;
        }
        return findNthEleInFib(n - 1) + findNthEleInFib(n - 2);
    }

}
