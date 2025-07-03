package com.kritsn.scaler.javaRefresher;

import java.util.Scanner;

public class Array1D {

    public static void main(String[] args) {
//        q2(args);
        q5(args);
    }

    /**
     * Write a program to input N numbers array from user and insert an element Y in it at specified position X.
     * Note: When an element is inserted at position X, all elements that were already present at position >= X, gets shifted to one position right.
     *
     * @param args
     */
    public static void q1(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int ele = scn.nextInt();
            arr[i] = ele;
        }

        int pos = scn.nextInt();
        int newEle = scn.nextInt();
        for (int i = pos - 1; i <= n; i++) {
            int temp = arr[i];
            arr[i] = newEle;
            newEle = temp;
        }
        printArray(arr);
    }

    /**
     * Write a program to print maximum and minimum elements of
     * the input array A of size N where you have to take integer N and other N elements as input from the user.
     *
     * @param args
     */
    public static void q2(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] arr = getArrayFromUser(scn);
        int min = arr[0];
        int max = arr[0];
        for (int a : arr) {
            if (min > a) {
                min = a;
            }
            if (a > max) {
                max = a;
            }
        }
        System.out.println(max + " " + min);
    }

    /**
     * Write a program to print sum of elements of the input array A of size N where you have to take integer N and
     * further N elements as input from user.
     *
     * @param args
     */
    public static void q3(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] arr = getArrayFromUser(scn);
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }
        System.out.println(sum);
    }

    /**
     * You are given a constant array A and an integer B.
     * <p>
     * You are required to return another array where outArr[i] = A[i] + B.
     *
     * @param args
     */
    public static void q4(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] arr = getArrayFromUser(scn);
        int b = scn.nextInt();
        q4Assist(arr, b);
    }

    public static int[] q4Assist(int[] arr, int b) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] += b;
        }
        return arr;
    }

    public static void q5(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int ele = scn.nextInt();
            arr[i] = ele;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
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
