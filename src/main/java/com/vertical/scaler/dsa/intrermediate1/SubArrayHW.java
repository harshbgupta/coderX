package com.vertical.scaler.dsa.intrermediate1;

import java.util.ArrayList;
import java.util.List;

public class SubArrayHW {

    /**
     * TC:
     * SC:
     */
    private static int[] q1(int[] A, int B) {
        List<Integer> ansList = new ArrayList<>();
        int length = A.length - ((2 * B + 1));
        for (int i = 0; i < length + 1; i++) {
            int currentValue = -1;
            boolean flag = true;
            if (length > 0) {
                for (int j = i + 1; j < i + length; j++) {
                    if (currentValue == A[j]) {
                        flag = false;
                        break;
                    }
                    currentValue = A[j];
                }
                if (flag) {
                    ansList.add(i + B); // b is the half length of subarray,so in any subarray half length will be (i+B)
                }
            }
        }
        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    /**
     * TC:
     * SC:
     */
    private static int[] q1test(int[] A, int B) {
        List<Integer> ansList = new ArrayList<>();
        int n = A.length;
        int len = 2 * B + 1;
        for (int i = 0; i < n - len + 1; i++) {
            int lastIndexValue = -1;
            boolean flag = true;
            for (int j = i; j < i + len; j++) {
                if (A[j] == lastIndexValue) {
                    flag = false;
                    break;
                }
                //updating last index to current index value
                lastIndexValue = A[j];
            }
            if (flag) {
                ansList.add(i + B);
            }
        }
        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }


    /**
     * TODO: Good Question
     * <p>
     * Given an array of integers A, a subarray of an array is said to be good if it fulfills any one of the criteria:
     * 1. Length of the subarray is be even, and the sum of all the elements of the subarray must be less than B.
     * 2. Length of the subarray is be odd, and the sum of all the elements of the subarray must be greater than B.
     * <p>
     * Your task is to find the count of good subarrays in A.
     * <p>
     * TC:
     * SC:
     */
    private static int q2(int[] A, int B) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int sum = 0; //carry forward-> sum carries the sum of last sub array
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                if ((j - i + 1) % 2 == 0 && sum < B) count++;
                if ((j - i + 1) % 2 != 0 && sum > B) count++;
            }
        }
        return count;
    }

    /**
     * Given an array A of N non-negative numbers and a non-negative number B,
     * you need to find the number of subarrays in A with a sum less than B.
     * We may assume that there is no overflow.
     * TC: O(N^2)
     * SC: O(1)
     */
    private static int q3(int[] A, int B) {
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            int sum = 0; //cary forward sum so it will contains the value of previous sub array
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                if (sum < B)
                    ans++;
                else
                    break;
            }
        }
        return ans;
    }
}
