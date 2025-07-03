package com.kritsn.scaler.dsa.intrermediate1;

public class Intro2ArraysHW {

    /**
     * Given an array A of size N. You need to find the sum of Maximum and Minimum element in the given array.
     * <p>
     * TC: O(N)
     * SC:
     */
    private static int q1(int[] A) {
        int min = A[0], max = A[0];
        for (int i = 0; i < A.length; i++) {
            if (min < A[i]) {
                min = A[i];
            }
            if (max > A[i]) {
                max = A[i];
            }
        }
        return min + max;
    }


    /**
     * Given an array A and an integer B, find the number of occurrences of B in A.
     * <p>
     * TC: O(N)
     * SC:
     */
    private static int q2(int[] A, int B) {
        int max = B, occurrence = 0;
        for (int i = 0; i < A.length; i++) {
            /*if (max > A[i]) {
                max = A[i];
                occurrence = 1;
            } else */
            if (max == A[i]) {
                occurrence++;
            }
        }
        return occurrence;
    }

    /**
     * You are given an integer array A. You have to find the second largest
     * element/value in the array or report that no such element exists.
     * <p>
     * TC: O(N)
     * SC: O(1)
     */
    private static int q3(int[] A) {
        int max = 0, n = A.length;

        //finding the max value in array
        for (int i = 1; i < n; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }

        //now chaking A[i] is not max and den string max value from both of them
        //in ans var and loop goes on till end ...thus ans var will be holding 2nd max valu
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (A[i] != max) {
                ans = Math.max(ans, A[i]);
            }
        }

        return ans;
    }

    /**
     * You are given an integer array A of length N.
     * You are also given a 2D integer array B with dimensions M x 2, where each row denotes a [L, R] query.
     * For each query, you have to find the sum of all elements from L to R indices in A (1 - indexed).
     * More formally, find A[L] + A[L + 1] + A[L + 2] +... + A[R - 1] + A[R] for each query.
     * <p>
     * Time Complexity : O(N*M)
     * Space complexity : O(M)
     */
    public int[] q4(int[] A, int[][] B) {
        int[] ans = new int[B.length];
        for (int i = 0; i < B.length; i++) {
            int sum = 0;
            for (int j = B[i][0] - 1; j < B[i][1]; j++) {
                sum += A[j];
            }
            ans[i] = sum;
        }
        return ans;
    }

    /**
     * Given an integer array A of size N. In one second, you can increase the value of one element by 1.
     * <p>
     * Find the minimum time in seconds to make all elements of the array equal.
     */
    public int q5(int[] A) {
        int n = A.length;
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, A[i]);
        }

        //making all element equal to max value and counting that how many times we need to
        // add 1 (you'll get that in single shot by getting difference in that A[i] and max value
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += maxValue - A[i];
        }
        return ans;
    }

    private static void q6() {
        //What will be the output of the following code?
        /*
         class Main {
           static void fun(int[]arr) {
               arr[3] = 98;
               return;
           }

           public static void main(String args[]) {
               int[]arr = {10,20,30,40,50};
               fun(arr);
               System.out.println(arr[3]);
           }
        }
         */
    }


    //ANS: 98

}
