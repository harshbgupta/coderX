package com.kritsn.ivs.wissen.r1Test1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 13, 2025
 */

public class MonkeyNdBanana {

    /**
     * ou must split N bananas equally among d monkeys such that:
     * •	d ≥ 2 (not all to one monkey)
     * •	N/d ≥ 2 (each monkey gets > 1 banana)
     * •	d | N (equal, integral share)
     *
     * Because any divisor d \ne N is \le N/2, these boil down to:
     *
     * Valid choices of monkeys = all non-trivial divisors of N (divisors d with 2 \le d \le N/2).
     * Number of ways = count of such divisors.
     * Feasible iff N is composite and N \ge 4 (for N=1,2,3 or prime N, there are 0 ways).
     *
     * ⸻
     *
     * Examples
     * •	N = 4 → divisors: {2, 4}. Valid d=\{2\} → each gets 4/2=2. 1 way.
     * •	N = 6 → divisors: {2, 3, 6}. Valid d=\{2,3\} → shares 3 or 2. 2 ways.
     * •	N = 12 → divisors: {2, 3, 4, 6, 12}. Valid d=\{2,3,4,6\} → shares {6,4,3,2}. 4 ways.
     * •	N = 7 (prime) → no valid d. 0 ways.
     */
    public static List<int[]> equalDistributions(int N) {
        List<int[]> res = new ArrayList<>();
        if (N < 4) return res; // impossible to give >=2 each with >=2 monkeys
        for (int d = 2; d * d <= N; d++) {
            if (N % d == 0) {
                int share = N / d;
                if (share >= 2) res.add(new int[]{d, share});   // d monkeys
                int other = N / d; // paired divisor
                if (other != d) { // avoid duplicate when d*d == N
                    int d2 = other;
                    int share2 = N / d2; // equals d
                    if (share2 >= 2 && d2 <= N / 2) res.add(new int[]{d2, share2});
                }
            }
        }
        // Sort by number of monkeys ascending
        res.sort(Comparator.comparingInt(a -> a[0]));
        return res;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Helper
    ///////////////////////////////////////////////////////////////////////////
    public static int countWays(int N) {
        int count = 0;
        if (N < 4) return 0;
        for (int d = 2; d <= N / 2; d++) {
            if (N % d == 0) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int N = 12;
        System.out.println("Ways: " + countWays(N));
        for (int[] w : equalDistributions(N)) {
            System.out.println(w[0] + " monkeys, " + w[1] + " bananas each");
        }
    }
}
