package com.vertical.scaler.javaRefresher;

import java.util.Scanner;

public class Pattern2HW {
    public static void main(String[] args) {
//        qPattern1(args);
        qPattern2(args);
//        qPattern3(args);
//        qPattern4(args);
    }

    /**
     * n=5
     * *_____*
     * *____*
     * *___*
     * *__*
     * *_*
     *
     * @param args
     */
    public static void qPattern1(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n + 3 - i; j++) {
                if (j == 1 || j == n + 3 - i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * n = 5
     * <p>
     * 0 0 0 0 5 0 0 0 0
     * 0 0 0 4 8 12 0 0 0
     * 0 0 3 6 9 12 15 0 0
     * 0 2 4 6 8 10 12 14 0
     * 1 2 3 4 5 6 7 8 9
     *
     * @param args
     */
    public static void qPattern2(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int temp = n;
        for (int i = 1; i <= n; i++) {
            int counter = temp;
            for (int j = 1; j <= 2 * n - 1; j++) {
                if ((i + j) >= (n + 1) && (i + j) <= (n + (2 * i) - 1)) {
                    System.out.print(counter + " ");
                    counter+= temp;
                } else {
                    System.out.print("0 ");
                }
            }
            temp--;
            System.out.println();
        }
    }

    /**
     * n=5
     * *
     * **
     * ***
     * ****
     * *****
     * ****
     * ***
     * **
     * *
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

        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }

    /**
     * n = 5
     * * * * * *
     * * * * *
     * * * *
     * * *
     * *
     *
     * @param args
     */
    public static void qPattern4(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++) {
                if (j >= n - i + 1)
                    System.out.print("* ");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
