package com.kritsn.scaler.dsa.intrermediate1;

public class PrefixSumHW {

    /**
     * Given an array of integers A, find and return the product array of the same size where the ith element of the
     * product array will be equal to the product of all the elements divided by the ith element of the array.
     * <p>
     * Note: It is always possible to form the product array with integer (32 bit) values. Solve it without
     * using the division operator.
     * <p>
     * TC:
     * SC:
     */
    private static int[] q4(int[] A) {
        int n = A.length;
        int[] pref = new int[n];
        int[] suff = new int[n];
        pref[0] = A[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] * A[i];
        }
        suff[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suff[i] = suff[i + 1] * A[i];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ans[i] = suff[i + 1];
            } else if (i == n - 1) {
                ans[i] = pref[i - 1];
            } else {
                ans[i] = pref[i - 1] * suff[i + 1];
            }
        }
        return ans;
    }

    /**
     * Given an array of integers A, find and return the product array of the same size where the ith element of the
     * product array will be equal to the product of all the elements divided by the ith element of the array.
     * <p>
     * Note: It is always possible to form the product array with integer (32 bit) values. Solve it without
     * using the division operator.
     * <p>
     * TC: O(N)
     * SC: O(N) creating ans array
     */
    private static int[] q4Alternate(int[] A) {
        int prod = 1;
        for (int ele : A) {
            prod *= ele;
        }
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            ans[i] = prod / A[i];
        }
        return ans;
    }

}
