package com.kritsn.scaler.dsa.intrermediate1;

import java.util.ArrayList;

public class SlidingWindow {

    public static void main(String[] args) {
        int[] A = {1, 12, 10, 3, 14, 10, 5};
        int B = 8;
        System.out.println(q2(A, B));
    }

    /**
     * //TODO: IMP
     * Given an array A of length N. Also given are integers B and C.
     * Return 1 if there exists a subarray with length B having sum C and 0 otherwise
     * <p>
     * TC: O(N)
     * SC: O(1)
     */
    private static int q1(int[] A, int B, int C) {
        //as subarray length has been given as B
        //so we will be using "sliding window" approach

        //S1: getting sum of first subarray of length sum
        int sum = 0;
        for (int i = 0; i < B; i++) {
            sum += A[i];
        }
//        System.out.println("sum -> " + sum);

        //checking id B is zero then, it's empty subarray sop return 0
        if (B == 0) return 0;

        //sliding window
        int s = 1, e = B, n = A.length;
        while (e < n) {
            sum = sum - A[s - 1] + A[e];
//            System.out.println("s " + s + " and e " + e + " sum -> " + sum);
            s++;
            e++;
            if (sum == C) return 1;
        }
        return 0;
    }

    /**
     * //TODO: IMP
     * Given an array of integers A and an integer B, find and return the minimum number of swaps required to
     * bring all the numbers less than or equal to B together.
     * Note: It is possible to swap any two elements, not necessarily consecutive.
     * <p>
     * TC: O(N)
     * SC: O(1)
     */
    private static int q2(int[] A, int B) {
        //as subarray length has been given as B
        //so we will be using "sliding window" approach

        //S1: counting good elements in first subarray which will become length of subarray
        int totalGoodElements = 0, n = A.length;
        for (int i = 0; i < n; i++) {
            if (A[i] <= B) totalGoodElements++; //counting good element
        }

        //S2: counting bad elements in first subarray
        int badElementsPresentInSubArray = 0;
        int lengthOfSubArray = totalGoodElements;
        for (int i = 0; i < lengthOfSubArray; i++) {
            if (A[i] > B) badElementsPresentInSubArray++; //counting bad element
        }

        //s3: sliding window
        int s = 1, e = lengthOfSubArray, ans = badElementsPresentInSubArray;
        while (e < n) {
            if (A[s - 1] > B) badElementsPresentInSubArray--;//this bad ele is not in iur subarray now
            if (A[e] > B) badElementsPresentInSubArray++;//this bad ele just came in iur subarray now

            //we need to keep record of min bad ele present in subarray
            if (ans > badElementsPresentInSubArray) ans = badElementsPresentInSubArray;
            s++;
            e++;
        }

        //this many swap means swap to remove bad ele with good ele
        return ans;
    }

    /**
     * //TODO: IMP
     * <p>
     * Given an integer A, generate a square matrix filled with elements from 1 to A2 in spiral order and return the generated square matrix.
     * TC: O(N^2)
     * SC:
     */
//    private static int[][] q3GenerateMatrix(int A) {
//        int n = A;
//        int i = 0, j = 0;
//        while (n > 1) {
//            //
//        }
//    }

    public ArrayList<ArrayList<Integer>> Q3GenerateMatrix(int A) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int rows, cols;
        int row, col;
        rows = cols = A;
        int num = 1;
        int max = A * A;
        for (int i = 0; i < rows; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < rows; j++) {
                res.get(i).add(0);
            }
        }
        row = col = 0;
        int dir = 0;
        int layer = 0;
        res.get(0).set(0, 1);
        for (int step = 2; step <= A * A; step++) {
            switch(dir) {
                // Go right
                case 0:
                    if (col == cols - layer - 1) {
                        row++;
                        dir = 1;
                    }
                    else
                        col++;
                    break;
                // Go down
                case 1:
                    if (row == rows - layer - 1) {
                        dir = 2;
                        col--;
                    } else
                        row++;
                    break;
                // Go left
                case 2:
                    if (col == layer) {
                        row--;
                        dir = 3;
                    } else
                        col--;
                    break;
                // Go up
                case 3:
                    if (row == layer + 1) {
                        dir = 0;
                        col++;
                        layer++;
                    } else
                        row--;
                    break;
            }
            res.get(row).set(col, step);
        }
        return res;
    }
}
