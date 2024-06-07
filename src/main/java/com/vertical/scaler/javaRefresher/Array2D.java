package com.vertical.scaler.javaRefresher;

import java.util.ArrayList;
import java.util.Scanner;

public class Array2D {
    public static void main(String[] args) {
        q1(args);
    }

    /**
     * Write a program to input an integer N and a N*N matrix Mat from user and print the matrix in wave form (column wise)
     * See example for clarifications regarding wave print.
     *
     * 4 1 2
     * 7 4 4
     * 3 7 4
     *
     * ans: 4 7 3 7 4 1 2 4 4
     */
    public static void q1(String[] args) {
        //TODO : Imp
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int mat[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {
                if ((col % 2) == 1) {
                    System.out.print(mat[n - 1 - row][col]);
                } else {
                    System.out.print(mat[row][col]);
                }
                System.out.print(' ');
            }
        }

        System.out.println();
        sc.close();
    }


    /**
     * Write a program to input an integer N and a N*N matrix Mat from user and print the matrix in wave form (Row wise)
     *
     * 4 1 2
     * 7 4 4
     * 3 7 4
     *
     * ans: 4 1 2 4 4 7 3 7 4
     * See example for clarifications regarding wave print.
     */
    public static void q2(String[] args) {
        //TODO : Imp
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int mat[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if ((row % 2) == 0) {
                    System.out.print(mat[row][col]);
                } else {
                    System.out.print(mat[row][n - 1 - col]);
                }
                System.out.print(' ');
            }
        }

        System.out.println();
        sc.close();
    }

    /**
     * You are given a 2D integer matrix A, return a 1D integer array containing row-wise sums of original matrix.
     */
    public ArrayList<Integer> q3(ArrayList<ArrayList<Integer>> A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (ArrayList<Integer> insideList : A) {
            int sum = 0;
            for (Integer data : insideList) {
                sum = sum + data;
            }
            ans.add(sum);
        }
        return ans;
    }

    /**
     * You are given two matrices A & B of same size, you have to return another matrix which is the sum of A and B.
     */
    public ArrayList<ArrayList<Integer>> q4(ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B) {
        //TODO: Imp
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        int row = A.size(), col = A.get(0).size();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
//                ans.get(i,(A.get(i).get(j)+ B.get(i).get(j)));
            }
        }
        return A;
    }
}
