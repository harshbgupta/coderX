package com.kritsn.scaler.dsa.intrermediate1;

import java.util.ArrayList;
import java.util.List;

public class CarryForward {
    public static void main(String[] args) {
//        Scanner scn = new Scanner(System.in);
//        System.out.println(q2NormalApproach(scn.next()));
//        main(args);
        int[] A ={16, 17, 4, 3, 5, 2};
        q3(A);
    }
    /**
     * Given A = [1, -2, 5, 2, -1, 6]. Choose all the valid subarrays of the array

            [5, 2, -1]          YES
            [-2, -1, 6]         NO
            [1, -2, 5, 2]       YES
            [6]                 YES
     */

    /**
     * You have given a string A having Uppercase English letters.
     * You have to find how many times subsequence "AG" is there in the given string.
     * NOTE: Return the answer modulo 109 + 7 as the answer can be very large.
     * <p>
     * TC: O(N^2) not optimised
     * SC: O(1)
     */
    private static int q2NormalApproach(String A) {
        char c1 = 'A';
        char c2 = 'G';
        int ans = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == c1) {
                for (int j = i + 1; j < A.length(); j++) {
                    if (A.charAt(j) == c2) ans++;
                }
            }
        }
        return ans;
    }

    /**
     * You have given a string A having Uppercase English letters.
     * You have to find how many times subsequence "AG" is there in the given string.
     * <p>
     * NOTE: Return the answer modulo 10^9 + 7 as the answer can be very large.
     * TODO: as this is asking ans in modulo 109 + 7 so in the and take mod of 10^9+7, so finalAns = ans % MOD
     * <p>
     * TC:
     * SC:
     */
    private static int q2OptimisedApproach(String A) {
        char c1 = 'A';
        char c2 = 'G';
        int count = 0, ans = 0, MOD = 1000 * 1000 * 1000 + 7;
        for (int i = A.length() - 1; i >= 0; i--) {
            if (A.charAt(i) == c2) {
                count++;
            } else if (A.charAt(i) == c1) {
                ans = ans + count;
            }
        }
        //as this is asking ans in modulo 109 + 7 so in the and take
        //mod of 10^9+7, so finalAns = ans % MOD
        // otherwise ans would be just 'ans'
        return ans;
    }

    /**
     * Given an integer array A containing N distinct integers, you have to find all the leaders in array A.
     * An element is a leader if it is strictly greater than all the elements to its right side.
     * NOTE:The rightmost element is always a leader.
     * <p>
     * TC:
     * SC:
     */
    private static int[] q3(int[] A) {
        int[] reverseCarryForwardArray = new int[A.length];
        //creating reverseCarryForwardArray in which left index value will be always br greater that it's
        // all right side values
        for (int i = A.length - 1; i >= 0; i--) {
            //running right to left
            if (i == A.length - 1) {
                //taking care of special case which is first operation
                reverseCarryForwardArray[A.length - 1] = A[A.length - 1];
            } else {
                if (A[i] > reverseCarryForwardArray[i + 1]) {
                    reverseCarryForwardArray[i] = A[i];
                } else {
                    reverseCarryForwardArray[i] = reverseCarryForwardArray[i+1];
                }
            }
        }

        //As right now length can be dynamic
        //as of now storing data in arrayList
        List<Integer> ansList = new ArrayList<>();
        for (int i = 0; i < reverseCarryForwardArray.length; i++) {
            if (i == 0) {
                //taking care of special case which is first operation
                ansList.add(reverseCarryForwardArray[0]);
            } else {
                if (reverseCarryForwardArray[i] != reverseCarryForwardArray[i - 1]) {
                    ansList.add(reverseCarryForwardArray[i]);
                }
            }
        }

        int [] ansArray = new int[ansList.size()];
        for (int i = 0; i<ansList.size(); i++){
            ansArray[i] =ansList.get(i);
        }

        return ansArray;
    }


    /**
     * Given an array A, find the size of the smallest subarray such that it contains at least one occurrence
     * of the maximum value of the array
     * <p>
     * and at least one occurrence of the minimum value of the array.
     * <p>
     * TC: O(N) + O(N)  = O(N)
     * SC: O(1)
     */
    private static int q4(int[] A) {
        //find max and min of array
        int max = A[0], min = A[0];
        for (int i : A) { //TC: O(N), SC: (1)
            if (i < min) min = i;
            if (i > max) max = i;
        }

        //taking care of special case if min and max value are same
        //e.g.: [4,4,4,4] so min length array containing min and max both will be [4]
        if (min == max)
            return 1;

        int pos_min = -1, pos_max = -1,
                ans = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            // last occurrence of minValue
            if (A[i] == min)
                pos_min = i;

            // last occurrence of maxValue
            if (A[i] == max)
                pos_max = i;

            //checking that min and max value position are not the pre-defined (-1)value
            //if not den
            if (pos_max != -1 && pos_min != -1)
                //if min and max value position are not the pre-defined (-1)value
                //den string/update the min length of array
                ans = Math.min(ans,
                        Math.abs(pos_min - pos_max) + 1);
        }

        return ans;
    }
}
