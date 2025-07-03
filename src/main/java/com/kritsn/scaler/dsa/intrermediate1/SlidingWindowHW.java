package com.kritsn.scaler.dsa.intrermediate1;

public class SlidingWindowHW {

    public static void main(String[] args) {
        int[] A = {3, 7, 5, 20, -10, 0, 12};
        int B = 2;
        System.out.println(q1(A, B));
    }

    /**
     * Given an array of size N, find the subarray of size B with the least average.
     * <p>
     * TC: O(N)
     * SC: O(1)
     */
    private static int q1(int[] A, int B) {
        //we can check for sum instead avg coz sum is directly proportional to avg
        //so we'll will check for sum instead for avg

        int n = A.length;
        //s1: find the avg of first subarray
        int lengthSubarray = B;
        int sum = 0;
        for (int i = 0; i < lengthSubarray; i++) {
            sum += A[i];
        }
//        System.out.println("sum -> " + sum);

        //s2: Sliding array
        int s = 1, e = lengthSubarray, leastAvgSum = sum;
        int index = 0;
        while (e < n) {
            sum = sum + A[e] - A[s - 1];
            if (sum < leastAvgSum) {
                leastAvgSum = sum;
                index = s;
            }
//            System.out.println("sum -> " + sum + " index ->" + index);
            s++;
            e++;
        }

        return index;
    }
}
