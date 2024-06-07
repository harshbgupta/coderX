package com.vertical.scaler.javaRefresher;

import java.util.Scanner;

public class Pattern1HW {
    public static void main(String[] args) {
//        qPattern1(args);
//        qPattern2(args);
//        qPattern3(args);
//        qPattern4(args);

        //TODO: Imp
        qPattern5(args);
//        qPattern6(args);
//        qPattern7(args);
    }

    /**
     * Given an integer N, print the corresponding pattern for N.
     * <p>
     * For example if N = 4 then pattern will be like:
     *
     * @param args
     */
    public static void qPattern1(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            String printMsg = "";
            for (int j = 1; j <= i; j++) {
                printMsg += j + " ";
            }
            System.out.print(printMsg.trim());
            System.out.println();
        }
    }

    /**
     * n = 4
     * ****
     * ***
     * **
     * *
     *
     * @param args
     */
    public static void qPattern2(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = n; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
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
    public static void qPattern3(String[] args) {
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
     * *****
     * _****
     * __***
     * ___**
     * ____*
     * "_" is space
     *
     * @param args
     */
    public static void qPattern4(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = n; i >= 1; i--) {
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
     * ********** // 0 spaces
     * ****__**** // 2 spaces
     * ***____*** // 4 spaces
     * **______** // 6 spaces
     * *________* // 8 spaces
     * "_" is space
     *
     * @param args
     */
    public static void qPattern5(String[] args) {
        //TODO: Imp
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        // Loop for N rows
        for(int i=1;i<=n;i++){

            // Print n-i+1 stars
            for(int j=1;j<=(n-i+1);j++) {
                System.out.print("*");
            }

            // Print 2*(i-1) spaces
            for(int j=1;j<=2*(i-1);j++) {
                System.out.print(" ");
            }

            // Print n-i+1 stars
            for(int j=1;j<=(n-i+1);j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    /**
     * ****
     * * *
     * **
     * *
     *
     * @param args
     */
    public static void qPattern6(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                if (i == 1 || i == n || j == 1 || j == i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * *****
     * *___*
     * *___*
     * *___*
     * *****
     * <p>
     * "_" is space
     */
    public static void qPattern7(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 || i == n || j == 1 || j == n)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
