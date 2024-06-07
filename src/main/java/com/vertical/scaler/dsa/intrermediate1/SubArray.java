package com.vertical.scaler.dsa.intrermediate1;

import java.util.ArrayList;

public class SubArray {

    /*
    Q1
    Given an array of size 21, find the total number of subarrays of the array.

    numberOfArrays = n(n+1)/2
    so ans  = 21*22/2 = 231
    */

    /*

     */


    /*
    Q2
    What is the time and space complexity for printing the sum of each subarray?
        void printSums(int ar[]){
            int n = ar.length;
            for(int i = 0 ; i < n ; i++){
                int sum = 0;
                for(int j = i ; j < n ; j++){
                    sum = sum + ar[j];
                    print(sum)
                }
            }
        }

        TC: O(N^2)
        SC: O(1)
    */


    /*
    Q3
    What is the time and space complexity for printing the sum of each subarray?
        void printSums(int ar[]){
            int n = ar.length;
            int pf[N];
            pf[0] = ar[0];
            for(int i = 1 ; i < n ; i++){=
                pf[i] = pf[i - 1] + ar[i];
            }
            for(int i = 0 ; i < n ; i++){
                for(int j = i ; j < n ; j++){
                    if(i == 0){
                        print(pf[j]);
                    } else{
                        print(pf[j] - pf[i - 1]);
                    }
                }
            }
    }

        TC: O(N^2)
        SC: O(N) -> Prefix Sum
    */

    /**
     * Given an array A of length N, return the subarray from B to C.
     * <p>
     * TC:
     * SC:
     */
    private static int[] q4(int[] A, int B, int C) {
        int[] ans = new int[C - B + 1];
        for (int i = B; i <= C; i++) {
            ans[i - B] = A[i];
        }
        return ans;
    }

    /**
     * Given an array A of length N, return the subarray from B to C.
     * TC: O(N^2)
     * SC: O(1)
     */
    private static int q5(int A, int B, int[] C) {
        int ans = 0;
        for (int i = 0; i < A; i++) {
            int sum = 0; //cary forward sum so it will contains the value of previous sub array
            for (int j = i; j < A; j++) {
                sum += C[j];
                if (sum <= B)
                    ans = Math.max(sum, ans);
                else
                    break;
            }

        }
        return ans;
    }

    /**
     * TODO: M Imp Ques asked in google and facebook
     * <p>
     * You are given an integer array A of length N.
     * You have to find the sum of all subarray sums of A.
     * <p>
     * More formally, a subarray is defined as a contiguous part of an array which we can obtain by deleting zero or more elements from either end of the array.
     * A subarray sum denotes the sum of all the elements of that subarray.
     * <p>
     * <p>
     * TC: O(N)
     * SC: O(N)
     */
    private static long q6(int[] A) {
        long sum = 0;
        for (int i = 0; i < A.length; i++) {
            //the formula the element comes in all sub array is (i+1)(n-i)
            int x = i + 1; //
            int y = A.length - i;
            sum = sum + (long) x * y * A[i];
        }
        return sum;
    }

    /**
     * You are given an array A of N integers.
     * Return a 2D array consisting of all the subarrays of the array
     * <p>
     * TC: O(N^3)
     * SC: O(N^3)
     */
    public ArrayList<ArrayList<Integer>> q7solve(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        int n = A.size();
        for(int i = 0 ; i < n ; i++){
            for(int j = i ; j <n ; j++){
                ArrayList<Integer> v = new ArrayList<Integer>();
                for(int k = i ; k <= j ; k++){
                    v.add(A.get(k));
                }
                ans.add(v);
            }
        }
        return ans;
    }


}
