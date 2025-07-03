package com.kritsn.scaler.javaRefresher;

import java.util.Scanner;

public class Array1DHW {

    public static void main(String[] args) {
//        q1(args);
//        q2(args);
        q3(args);
    }

    /**
     * Write a program to print all negative numbers from input array A of size N where you have to take integer N
     * nd further N elements as input from user.
     *
     * @param args
     */
    public static void q1(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] arr = getArrayFromUser(scn);
        for (int a : arr) {
            if (a < 0) {
                System.out.print(a + " ");
            }
        }
    }

    /**
     * You are given T(number of test cases) integer arrays. For each array A, you have to find the value of absolute
     * difference between the counts of even and odd elements in the array.
     *
     * @param args
     */
    public static void q2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }

            int cnte = 0;
            int cnto = 0;

            for (int i = 0; i < N; i++) {

                if (A[i] % 2 == 1) {
                    cnto++;
                } else {
                    cnte++;
                }
            }

            System.out.println(Math.abs(cnte - cnto));
        }
    }

    /**
     * You are given an integer T denoting the number of test cases. For each test case, you are given an integer array A.
     *
     * You have to print the odd and even elements of array A in 2 separate lines.
     *
     * NOTE: Array elements should have the same relative order as in A.
     * @param args
     */
    public static void q3(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            Long[] A = new Long[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextLong();
            }

            String odd = "";
            String even = "";

            for (int i = 0; i < N; i++) {
                if (A[i] % 2 == 1) {
                    odd = odd + A[i]+" ";
                } else {
                    even = even + A[i]+" ";
                }
            }

            System.out.println(odd);
            System.out.println(even);
        }
    }


    /**
     * You are given an integer T denoting the number of test cases. For each test case, you are given an integer array A.
     *
     * You have to print the odd and even elements of array A in 2 separate lines.
     *
     * NOTE: Array elements should have the same relative order as in A.
     * @param args
     */
    public static void q3_1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while(T-- > 0){
            int N = sc.nextInt();
            int[] A = new int[N];
            int cnto = 0;
            int cnte = 0;

            for (int i = 0; i < N; i++){
                A[i] = sc.nextInt();
                if(A[i] % 2 == 1){
                    cnto++;
                }
                else{
                    cnte++;
                }
            }
            int[] B = new int[cnto];
            int[] C = new int[cnte];
            int ptrB = 0;
            int ptrC = 0;
            for (int i = 0; i < N; i++){
                if(A[i] % 2 ==  1){
                    B[ptrB] = A[i];
                    ptrB++;
                }
                else{
                    C[ptrC] = A[i];
                    ptrC++;
                }
            }
            for (int i = 0; i < cnto; i++){
                System.out.print(B[i] + " ");
            }
            System.out.println();
            for (int i = 0; i < cnte; i++){
                System.out.print(C[i] + " ");
            }
            System.out.println();
        }
    }

    public static void q4(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] arr = getArrayFromUser(scn);
        int[] res = new int[arr.length - 1];
        int pos = scn.nextInt();
        int posCounter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != pos - 1) {
                res[posCounter] = arr[i];
                posCounter++;
            }
        }
        printArray(res);
    }

    /**
     * Write a program that returns the product of all elements present in the list.Write a program that
     * returns the product of all elements present in the list.
     *
     * @param args
     * @return
     */
    public static Long q5(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] arr = getArrayFromUser(scn);
        Long mul = 1l;
        for (int a : arr) {
            mul = mul * a;
        }
        return mul;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Common Method
    ///////////////////////////////////////////////////////////////////////////
    private static void printArray(int[] arr) {
        String s = "";
        for (int a : arr) {
            s = s + a + " ";
        }
        System.out.println(s);
    }

    private static int[] getArrayFromUser(Scanner scn) {
        int n = scn.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int ele = scn.nextInt();
            arr[i] = ele;
        }
        return arr;
    }
}
