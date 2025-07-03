package com.kritsn.scaler.javaRefresher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayList1DHW {
    public static void main(String[] args) {
        q1();
    }

    /**
     * You are given an integer T (number of test cases). You are given array A and an integer B for each test case.
     * You have to tell whether B is present in array A or not.
     */
    public static void q1() {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        List<Long> array = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            array.add(scn.nextLong());
        }

    }

    /**
     * You are given an integer T (number of test cases). You are given array A and an integer B for each test case.
     * You have to tell whether B is present in array A or not.
     */
    public static Long q1_Assist(ArrayList<Integer> array) {
        Long product = 1l;
        for (int i = 0; i < array.size(); i++) {
            product = product * array.get(i);
        }
        return product;
    }

    /**
     * You are given an integer array A and an integer B.
     * You are required to return the count of pairs having sum equal to B.
     * NOTE: pair (i,j) and (j,i) are same.
     */
    public int q2(ArrayList<Integer> A, int B) {
        //TODO: Imp
        int count = 0;
        for (int i = 0; i < A.size(); i++) {
            for (int j = i + 1; j < A.size(); j++) {
                if (A.get(i) + A.get(j) == B)
                    count++;
            }
        }
        return count;
    }

    /**
     * Write a function to check if the input list has consecutive duplicate elements or not.
     * Return True if there are consecutive duplicate elements in the list else return False.
     */
    public boolean q3(int arr[]) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i]==(arr[i+1]))
                return true;
        }
        return false;
    }

    /**
     * You are provided with an integer array A. Return another array B of size same as that of A
     * such that B[i] = A[i]3 for 1 <= i <= |A| (1 based indexing).
     */
    public ArrayList<Long> q4(ArrayList<Integer> A) {
        ArrayList<Long> ans = new ArrayList<Long>();
        for (int i = 0; i < A.size(); i++) {
            Long x = new Long(A.get(i));
            ans.add(x * x * x);
        }
        return ans;
    }

    /**
     * You are given an integer array A, you have to return an integer array of same size whose ith element is the
     * frequency count of A[i] in array A .You are given an integer array A.
     */
    public ArrayList<Integer> q5(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < A.size(); i++) {
            int a = A.get(i);
            int count = 0;
            for (int j = 0; j < A.size(); j++) {
                if (a == A.get(j))
                    count++;
            }
            ans.add(count);
        }
        return ans;
    }
}
