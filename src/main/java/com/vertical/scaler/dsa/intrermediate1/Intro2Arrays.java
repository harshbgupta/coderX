package com.vertical.scaler.dsa.intrermediate1;

public class Intro2Arrays {
    public static void main(String[] args) {

    }

    /**
     * Given an array A of N integers. Count the number of elements that have at least 1
     * elements greater than itself.
     * <p>
     * TC: O(N)
     * SC: O(1)
     */
    private static int q1(int[] A) {
        int maxValue = 0, maxValueFrequency = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > maxValue) {
                //found new max value
                maxValue = A[i];
                maxValueFrequency = 1;
            } else if (A[i] == maxValue) {
                maxValueFrequency++;
                //found same max value again so increase the counter
            }
        }
        //as the required ia all elements except max Value
        //so removing the frequency of max value will bee the remaining number which are smaller than max value
        // and that's the answer
        return A.length - maxValueFrequency;
    }

    /**
     * Given an array A and an integer B. A pair(i, j) in the array is a good pair if i != j and (A[i] + A[j] == B).
     * Check if any good pair exist or not.
     * <p>
     * TC: O(N^2)
     * SC: O(1)
     */
    private static int q2(int[] A, int B) {
        for (int i = 0; i < A.length; i++) {
            //starting J from i as previous value of we have already found pair
            // if we start from j = 0 den we'll get duplicate pairs
            for (int j = i; j < A.length; j++) {
                if (i != j && A[i] + A[j] == B) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * Given an array A of N integers. Also given are two integers B and C.
     * Reverse the array A in the given range [B, C]
     * <p>
     * itr: (B-C)/2
     * TC: O(N)
     * SC: O(1)
     */
    public static int[] q3ReverseArray(int[] A, int B, int C) {
        int i = B , j = C;
        while(i < j){
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i += 1;
            j -= 1;
        }
        return A;
    }

    /**
     * You are given a constant array A.
     * You are required to return another array which is the reversed form of the input array.
     *
     * itr: (B-C)/2
     * TC: O(N)
     * SC: O(1)
     */
    public int[] q4(int[] A) {
        int i = 0 , j = A.length-1;
        while(i < j){
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i += 1;
            j -= 1;
        }
        return A;
    }

    /**
     * Given an integer array A of size N and an integer B, you have to return the
     * same array after rotating it B times towards the right.
     *
     * itr: 3N
     * TC: O(N)
     * SC: O(1)
     */
    private static int[] q5(int[] A, int B){
        int n = A.length;
        B = B % n;
        q3ReverseArray(A, 0, n - 1);
        q3ReverseArray(A, 0, B - 1);
        q3ReverseArray(A, B, n - 1);
        return A;
    }


    private static void q6(){
    }
    /*
     *What is the time complexity for inserting/deleting at the beginning of the array?
     *
     * TC: O(N)
     * Exp: All the other elements will have to be moved, hence O(n).
     * but ArrayList add will take O(1) and for remove is O(n) as you have to shuffle the elements to the "right" of
     * that point "left" by one:
     *
     * But As in this question we need to add th element at beginning So, for arrayList also add will take O(n) as it
     * would require to shift all the existing elements by one position.
     */
}
