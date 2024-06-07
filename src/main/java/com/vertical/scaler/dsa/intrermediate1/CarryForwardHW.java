package com.vertical.scaler.dsa.intrermediate1;

import java.util.TreeSet;

public class CarryForwardHW {
    public static void main(String[] args) {

    }

    /**
     * Say you have an array, A, for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     *
     * Return the maximum possible profit.
     * TC:
     * SC:
     */
    public static int q1MaxProfit(final int[] A) {
        // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
        if (A.length == 0 || A.length == 1) {
            return 0;
        }

        int max = A[A.length - 1];

        int profit = 0;
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > max) {
                max = A[i];
            }
            // max stores the maximum value to the right of A[i]
            profit = Math.max(profit, max - A[i]);
        }

        return profit;

    }

    /**
     * You have given a string A having Uppercase English letters.
     * You have to find how many times subsequence "AG" is there in the given string.
     * NOTE: Return the answer modulo 109 + 7 as the answer can be very large.
     * <p>
     * TC:
     * SC:
     */
    private static int q2Solve(String A) {
        int n = A.length(), ans = 0, MOD = 1000 * 1000 * 1000 + 7;
        int cnt_G[] = new int[n], count = 0;

        //Suffix count of G
        for (int i = n - 1; i >= 0; i--) {
            if (A.charAt(i) == 'G')
                count = count + 1;
            cnt_G[i] = count;
        }

        // traverse the string again from beginning
        for (int i = 0; i < n; i++) {
            // if current character is "A" then add number of G's after that
            if (A.charAt(i) == 'A') {
                ans = ans + cnt_G[i];
                ans = ans % MOD;
            }
        }
        return ans;

    }

    /**
     * //TODO : Revisit
     * You are given a string S, and you have to find all the amazing substrings of S.
     * An amazing Substring is one that starts with a vowel (a, e, i, o, u, A, E, I, O, U).
     * <p>
     * TC:
     * SC:
     */
    private static int q3MyApproch(String A) {
        int count = 0, ans = 0, MOD = 1000 * 1000 * 1000 + 7;
        for (int i = A.length() - 1; i >= 0; i--) {
            if (A.charAt(i) != 'a' && A.charAt(i) != 'e' && A.charAt(i) != 'i' && A.charAt(i) != 'o' && A.charAt(i) != 'u' &&
                    A.charAt(i) != 'A' && A.charAt(i) != 'E' && A.charAt(i) != 'I' && A.charAt(i) != 'O' && A.charAt(i) != 'U') {
                count++;
            } else if (A.charAt(i) == 'a' || A.charAt(i) == 'e' || A.charAt(i) == 'i' || A.charAt(i) == 'o' || A.charAt(i) == 'u' ||
                    A.charAt(i) == 'A' || A.charAt(i) == 'E' || A.charAt(i) == 'I' || A.charAt(i) == 'O' || A.charAt(i) == 'U') {
                ans = ans + count;
            }
        }
        return ans%MOD;
    }

    /**
     * TC:
     * SC:
     */
    private static int q3ScalerHint(String A) {
        TreeSet<Character> se = new TreeSet<>();
        se.add('a'); se.add('e'); se.add('i'); se.add('o'); se.add('u');
        se.add('A'); se.add('E'); se.add('I'); se.add('O'); se.add('U');

        int n = A.length();
        long ans = 0;
        for(int i = 0; i < n; i++){
            if(se.contains(A.charAt(i))){
                ans += (n - i);
            }
        }
        return (int)(ans%10003);

    }

    /**
     * You are given an integer array A.
     * Decide whether it is possible to divide the array into one or more
     * subarrays of even length such that the first and last element of all subarrays will be even.
     * Return "YES" if it is possible; otherwise, return "NO" (without quotes).
     * TC:
     * SC:
     */
    private static String q4EvenSubArrays(int[] A) {
        int n = A.length;
        if(A[0]%2 == 0 && A[n-1]%2 == 0 && n%2 == 0)
            return "YES";
        return "NO";

    }

    /**
     * A wire connects N light bulbs.
     *
     * Each bulb has a switch associated with it; however, due to faulty wiring,
     * a switch also changes the state of all the bulbs to the right of the current bulb.
     * Given an initial state of all bulbs, find the minimum number of switches you have to press to
     * turn on all the bulbs.
     * You can press the same switch multiple times.
     * Note: 0 represents the bulb is off and 1 represents the bulb is on.
     * TC:
     * SC:
     */
    private static int q7Bulbs(int[] A) {
        int state = 0, ans = 0;

        // state variable will represent the state which we have to change.
        for (int i = 0; i < A.length; i++) {

            if (A[i] == state) {
                ans++;
                // As we will switch this, all the bulbs on right side will also change. So, change state = 1 - state
                state = 1 - state;
            }
        }
        return ans;
    }

    /**
     * You are given an integer array A of size N.
     * You have to pick B elements in total. Some (possibly 0) elements from left end of array A and some
     * (possibly 0) from the right end of array A to get the maximum sum.
     *
     * Find and return this maximum possible sum.
     *
     * NOTE: Suppose B = 4, and array A contains 10 elements, then
     *
     * You can pick the first four elements or can pick the last four elements, or can pick 1 from front and
     * 3 from the back, etc. You need to return the maximum possible sum of elements you can pick.
     * TC: O(N)
     * SC: O(N)
     */
    private static int q8PickFromBothSides(int[] A, int B) {
        int n = A.length;
        int [] suff = new int[n + 1];
        suff[n] = 0;
        suff[n - 1] = A[n - 1];
        for(int i = n - 2; i >= 0; i--){
            suff[i] = A[i] + suff[i + 1];
        }
        int pref_sum = 0;
        int ans = suff[n - B];
        for(int i = 0; i < B; i++){
            pref_sum = pref_sum + A[i];
            int suff_sum = suff[n - B + (i + 1)];
            ans = Math.max(ans, pref_sum + suff_sum);
        }
        return ans;

    }
}
