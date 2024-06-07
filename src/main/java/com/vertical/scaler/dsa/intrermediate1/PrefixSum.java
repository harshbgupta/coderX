package com.vertical.scaler.dsa.intrermediate1;

public class PrefixSum {
    public static void main(String[] args) {
        int[] A = {1, 2, 5, 1, 2};
        System.out.println(q5EquilibriumIndexOfArray(A));
    }

    /**
     * q1: The prefix sum formula for an array A of N integers is given by :-
     *
     * ans: prefSum[i] = prefSum[i - 1] + A[i]
     */

    /**
     * What is the time complexity of creating the prefix sum array of an array A of N integers ?
     *
     * and: O(N)
     */

    /**
     * You are given an integer array A of length N.
     * You are also given a 2D integer array B with dimensions M x 2, where each row denotes a [L, R] query.
     * For each query, you have to find the sum of all elements from L to R indices in A (1 - indexed).
     * More formally, find A[L] + A[L + 1] + A[L + 2] +... + A[R - 1] + A[R] for each query.
     *
     * TC: O(N)
     * SC: O(N)
     */
    private static long[] q3RangeSum(int[] A, int[][] B) {
        int n = A.length;
        int m = B.length;
        long[] pref = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            pref[i] = pref[i - 1] + A[i - 1];   //Sum from the 0th to the i-1'th index
        }
        long[] ans = new long[m];
        for (int i = 0; i < m; i++) {
            ans[i] = pref[B[i][1]] - pref[B[i][0] - 1];
        }
        return ans;
    }

    /**
     * TODO: Prefix Sum code
     * Given an array A of N integers. Construct prefix sum of the array in the given array itself.
     */
    private static int[] q4PrefixSum(int[] A) {
        int[] prefixSum = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if (i == 0) prefixSum[0] = A[0];
            else {
                prefixSum[i] = prefixSum[i - 1] + A[i];
            }
        }
        return prefixSum;
    }

    /**
     * it's not working for the case int[] A = {1, 2, 5, 1, 2}; equilibrium index 0
     * 
     * You are given an array A of integers of size N.
     * Your task is to find the equilibrium index of the given array
     * The equilibrium index of an array is an index such that the sum of elements at lower indexes is 
     * equal to the sum of elements at higher indexes.
     * NOTE:
     * Array indexing starts from 0.
     * If there is no equilibrium index then return -1.
     * If there are more than one equilibrium indexes then return the minimum index.
     * 
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private static int q5EquilibriumIndexOfArray(int[] A) {
        int[] prefixSumArray = q4PrefixSum(A);

        //prefixSumArray.length == 0 and A.length ==0 both are same as both have same length always
        int n = prefixSumArray.length;
        if (n < 3) return -1;
        for (int i = 2; i < n; i++) {

            if (prefixSumArray[i - 1] == prefixSumArray[n - 1] - prefixSumArray[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * it's working
     *
     * You are given an array A of integers of size N.
     * Your task is to find the equilibrium index of the given array
     * The equilibrium index of an array is an index such that the sum of elements at lower indexes is 
     * equal to the sum of elements at higher indexes.
     * NOTE:
     * Array indexing starts from 0.
     * If there is no equilibrium index then return -1.
     * If there are more than one equilibrium indexes then return the minimum index.
     *
     * TC: O(N)
     * SC: O(N)
     */
    private static int q5AlternateSolution(int[] A) {
        int[] prefixSumArray = q4PrefixSum(A);
        int[] suffixSumArray = q5SuffixSum(A);
        for (int i = 0; i < prefixSumArray.length; i++) {
            if (prefixSumArray[i] == suffixSumArray[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param A
     * @return
     */
    private static int[] q5SuffixSum(int[] A) {
        int[] suffixSum = new int[A.length];
        for (int i = A.length - 1; i >= 0; i--) {
            if (i == A.length - 1) suffixSum[A.length - 1] = A[A.length - 1];
            else {
                suffixSum[i] = suffixSum[i + 1] + A[i];
            }
        }
        return suffixSum;
    }

    /**
     * You are given an array A of length N and Q queries given by the 2D array B of size Q*2. Each query consists of two integers B[i][0] and B[i][1].
     * For every query, the task is to find the count of even numbers in the range A[B[i][0]…B[i][1]].
     *
     * TC: O(N+Q)
     * SC: O(N+Q)
     */
    private static int[] solve(int[] A, int[][] B) {
        int n = A.length;
        int[] pref = new int[n + 1];
        pref[0] = 0;
        for(int i = 0 ; i < n ; i++){
            if(A[i] % 2 == 0){
                pref[i + 1] = pref[i] + 1;
            }
            else{
                pref[i + 1] = pref[i];
            }
        }
        int[] ans = new int [B.length];
        for(int i = 0 ; i < B.length ; i++){
            ans[i] = pref[B[i][1] + 1] - pref[B[i][0]];
        }
        return ans;
    }

}
