package com.kritsn.scaler.dsa.intrermediate1;

public class ArrayInterviewQues1CC {
    public static void main(String[] args) {

    }

    /**
     * Given a binary array, find max number of consecutive 1s, and you are replace one 0 to 1
     * A={1,1,1,0,1,1,0,1,1,1,1,0,0,1,1} ans  = 2+4+1 =7 we are replacing 6th index 0 to 1
     */
    public static int q1(int[] arr) {
        //Edge cases
        int count = 0;
        for (int i : arr) {
            if (i == 1) count++;
        }
        if (count == arr.length) return arr.length; //if arr has all element as 1
        if (count == 0) return 1; //if arr has all element 0 den wee can replace any zero to 1 and ans will be 1
        //edge case done

        //now real coding
        int ans = 0;
        //i ==1  and i<arr.length -1 is coz wee do not need to check if first and last index are zero
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] == 0) {
                //check consecutive 1 on left side
                int leftCount = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] == 1) leftCount++;
                    else break;
                }

                //check consecutive 1 on right side
                int rightCount = 0;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] == 1) rightCount++;
                    else break;
                }

                //now update the ans
                if (ans < leftCount + rightCount + 1) ans = leftCount + rightCount + 1;
            }
        }
        return ans;
    }

    /**
     * Given a binary array, find max number of consecutive 1s, and you are swap any one element
     * A={1,1,1,0,1,1,0,1,1,1,1,0,0,1,1} ans  = 2+4+1 =7 we are replacing 6th index can be any other 1
     */
    public static int q2(int[] arr) {
        //Edge cases
        int count = 0;
        for (int i : arr) {
            if (i == 1) count++;
        }
        if (count == arr.length) return arr.length; //if arr has all element as 1
        if (count == 0) return 1; //if arr has all element 0 den wee can replace any zero to 1 and ans will be 1
        //edge case done

        //now real coding
        int ans = 0;
        //i ==1  and i<arr.length -1 is coz wee do not need to check if first and last index are zero
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] == 0) {
                //check consecutive 1 on left side
                int leftCount = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] == 1) leftCount++;
                    else break;
                }

                //check consecutive 1 on right side
                int rightCount = 0;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] == 1) rightCount++;
                    else break;
                }
                int ansTemp = leftCount + rightCount;

                //now update the ans
                if (ansTemp < count) ansTemp += 1;//if extra one is present den add 1 else leave it like that
                if (ans < ansTemp) ans = ansTemp;
            }
        }
        return ans;
    }

    /**
     * TC: O(N^3)
     * SC:O(1)
     * <p>
     * find the triplets. find max number of possible combination of three elements which fulfill the following condition
     * i<j<k && A[i] < A[j] < A[k]
     * Normal Approch
     */
    public static int q3(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] < arr[j] && arr[j] < arr[k])
                        count++;
                }
            }
        }
        return count;
    }

    /**
     * TC: O(N^2)
     * SC:O(1)
     * <p>
     * find the triplets. find max number of possible combination of three elements which fulfill the following condition
     * i<j<k && A[i] < A[j] < A[k]
     * Normal Approch
     */
    public static int q3Optimised(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 1; i < n - 1; i++) {

            //left transverse
            int leftCount = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) leftCount++;
            }


            int rightCount = 0;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) rightCount++;
            }
            int triplets = leftCount*rightCount;
            count+=triplets;
        }
        return count;
    }
}
