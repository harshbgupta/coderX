package com.kritsn.scaler.javaRefresher;

import java.util.Scanner;

public class Pattern1 {
    public static void main(String[] args) {
//        qPattern1(args);
//        qPattern2(args);
//        qPattern3(args);
//        qPattern4(args);
//        qPattern5(args);
//        qPattern6(args);
        qPattern6_1(args);
//        qPattern7(args);
    }


    /**
     * *****
     *
     * @param args
     */
    public static void qPattern1(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
    }

    /**
     * ****
     * ****
     * ****
     *
     * @param args
     */
    public static void qPattern2(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * *
     * **
     * ***
     * ****
     *
     * @param args
     */
    public static void qPattern3(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * 1
     * 1_
     * 1_3
     * 1_3_
     * 1_3_5
     * 
     * "_" is space
     *
     * @param args
     */
    public static void qPattern4(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (j % 2 == 0)
                    System.out.print(" ");
                else
                    System.out.print(j);
            }
            System.out.println();
        }
    }

    /**
     * *___*
     * *___*
     * *___*
     * *___*
     * *___*
     * "_" is space
     *
     * @param args
     */
    public static void qPattern5(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == 1 || j == n)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * ____*
     * ___**
     * __***
     * _****
     * *****
     * "_" is space
     *
     * @param args
     */
    public static void qPattern6(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= n - i + 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * ____*
     * ___**
     * __***
     * _****
     * *****
     * "_" is space
     *
     * @param args
     */
    public static void qPattern6_1(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * 7
     * <p>
     * 1 2 3 4 5 6 7
     * 1 2 3 4 5 6
     * 1 2 3 4 5
     * 1 2 3 4
     * 1 2 3
     * 1 2
     * 1
     *
     * @param args
     */
    public static void qPattern7(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = n; i >= 1; i--) {
            String printMsg = "";
            for (int j = 1; j <= i; j++) {
                printMsg += j + " ";
            }
            System.out.print(printMsg.trim());
            System.out.println();
        }
    }
}
