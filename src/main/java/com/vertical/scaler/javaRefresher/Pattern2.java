package com.vertical.scaler.javaRefresher;

import java.util.Scanner;

public class Pattern2 {
    public static void main(String[] args) {
//        qPattern1(args);
//        qPattern2(args);
//        qPattern3(args);
//        qPattern4(args);
        qPattern4_1(args);
    }

    /**
     * n=4
     * *________* // 8 spaces
     * <p>
     * **______** // 6 spaces
     * <p>
     * ***____*** // 4 spaces
     * <p>
     * ****__**** // 2 spaces
     * <p>
     * ********** // 0 spaces
     *
     * @param args
     */
    public static void qPattern1(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 2 * n; j++) {
                if ((i + j) > (2 * i) && (i + j) < (2 * n + 1)) {
                    System.out.print(" ");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    /**
     * n = 4
     * ********** // 0 spaces
     * <p>
     * ****__**** // 2 spaces
     * <p>
     * ***____*** // 4 spaces
     * <p>
     * **______** // 6 spaces
     * <p>
     * *________* // 8 spaces
     *
     * @param args
     */
    public static void qPattern2(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 2 * n; j++) {
                if ((i + j) > (n + 1) && (i + j) < (n + 2 * i)) {
                    System.out.print(" ");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    /**
     * n = 5
     * 0 0 0 0 1 0 0 0 0
     * 0 0 0 2 3 2 0 0 0
     * 0 0 3 4 5 4 3 0 0
     * 0 4 5 6 7 6 5 4 0
     * 5 6 7 8 9 8 7 6 5
     *
     * @param args
     */
    public static void qPattern3(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            int counter = i;
            for (int j = 1; j <= 2 * n-1; j++) {
                if ((i + j) >= (n + 1) && (i + j) <= (n + (2 * i) - 1)) {
                    if (j < n) {
                        System.out.print(counter+" ");
                        counter++;
                    } else if(j==n){
                        System.out.print(counter+" ");
                    }else {
                        counter--;
                        System.out.print(counter+" ");
                    }
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    /**
     * n = 5
     *     *
     *    * *
     *   * * *
     *  * * * *
     * * * * * *
     * @param args
     */
    public static void qPattern4(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 2 * n - 1; j++) {
                if ((i + j) >= (n + 1) && (i + j) <= (n + (2 * i) - 1)) {
                    if (n%2!=0) {
                        if ((i + j) % 2 == 0)
                            System.out.print("*");
                        else
                            System.out.print(" ");
                    } else {
                        if ((i + j) % 2 == 0)
                            System.out.print(" ");
                        else
                            System.out.print("*");
                    }
                } else {
                    System.out.print(" ");
                }
                if ((i + j) > (n + (2 * i) - 1))
                    break;
            }
            System.out.println();
        }
    }

    /**
     * n =5
     *     *
     *    * *
     *   * * *
     *  * * * *
     * * * * * *
     *
     * n=2
     *   *
     *  * *
     *
     *  n=3
     *    *
     *   * *
     *  * * *
     *
     *  n=6
     *      *
     *     * *
     *    * * *
     *   * * * *
     *  * * * * *
     * * * * * * *
     * @param args
     */
    public static void qPattern4_1(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
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
