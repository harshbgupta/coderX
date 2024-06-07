package com.vertical.scaler.javaRefresher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayList1D {
    public static void main(String[] args) {

    }

    /**
     * You are given an integer T (number of test cases). You are given array A and an integer B for each test case.
     * You have to tell whether B is present in array A or not.
     *
     * @param args
     */
    public static void q1(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        List<Long> array = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            array.add(scn.nextLong());
        }
        Long b = scn.nextLong();
        if (array.contains(b)) {
            System.out.println(1);
        } else System.out.println(0);
    }

    public static void q1_1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0) {
            int N = sc.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }
            int B;
            B = sc.nextInt();
            int found = 0;
            for (int i = 0; i < N; i++) {
                if (A[i] == B) {
                    found = 1;
                }
            }
            System.out.println(found);
        }
    }


    /**
     * Write a program that returns the list of elements that are present in the given list
     * and are divisible by 5 and 7.
     *
     * @param arr
     * @return
     */
    public static ArrayList<Integer> q2(ArrayList<Integer> arr) {
        ArrayList<Integer> arrNew = new ArrayList<>();
        for (Integer i : arr) {
            if (i % 5 == 0 && i % 7 == 0) {
                arrNew.add(i);
            }

        }
        return arrNew;
    }

    /**
     * Given an integer array A. Find and return the frequency of number B in array A.
     *
     * @param A
     * @param B
     * @return
     */
    public int q3(ArrayList<Integer> A, int B) {
        int count = 0;
        for (Integer a : A) {
            if (a == B)
                count++;
        }
        return count;
    }

    /**
     * Given an array A, check if it is sorted in non decreasing order or not.
     */
    public static int q4(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i + 1)) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * Given an Array of integers A, every element in it is repeated twice except one, find that unique element.
     */
    public static int q5(ArrayList<Integer> A) {
        int ans = 0;
        for (int i = 0; i < A.size(); i++) {
            int cnt = 0;
            for (int j = 0; j < A.size(); j++) {
                if (A.get(j) == A.get(i))
                    cnt++;
            }
            if (cnt == 1) {
                ans = A.get(i);
                break;
            }
        }
        return ans;
    }

    public static int q5_1(ArrayList<Integer> A) {
        int ans = 0;
        for (int i = 0; i < A.size(); i++) {
            int cnt = 0;
            for (int j = 0; j < A.size(); j++) {
                if (A.get(j).equals(A.get(i)))
                    cnt++;
            }
            if (cnt == 1) {
                ans = A.get(i);
                break;
            }
        }
        return ans;
    }
}
