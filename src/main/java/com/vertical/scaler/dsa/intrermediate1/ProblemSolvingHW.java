package com.vertical.scaler.dsa.intrermediate1;

import java.util.ArrayList;
import java.util.Scanner;

public class ProblemSolvingHW {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println(q1(scn.nextInt()));


        main(args);
    }

    /**
     * You are given an integer A. You have to tell whether it is a perfect number or not.
     * <p>
     * Perfect number is a positive integer which is equal to the sum of its proper positive divisors.
     */
    private static int q1(int A) {
        if (A == 1) {
            return 0;
        }
        int sum = 1;
        for (int j = 2; j * j <= A; j++) {
            if (A % j == 0) {
                sum += j;
                if (j != A / j) {
                    sum += A / j;
                }
            }
        }
        if (sum == A) {
            return 1;
        }
        return 0;
    }


    /**
     * You will be given an integer n. You need to return the count of prime numbers less than or equal to n.
     */
    private static int q2(int A) {
        int count = 0;
        for (int i = 2; i <= A; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isPrime(int A) {
        for (int i = 2; i <= Math.sqrt(A); i++) {
            if (A % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * You are given a function that takes an integer argument A. Return 1 if A is a perfect square
     * otherwise return 0.
     */
    private static int q3(int A) {
        for (int i = 0; i <= Math.sqrt(A); i++) {
            if (i * i == A)
                return 1;
        }
        return 0;
    }

    /**
     *Given two integers A and B. A represents the count of mangoes and B represent the count of slices of mangoes.
     *Mango can be cut into three slices of mangoes. A glass of mango shake can be formed by two slices of mangoes.
     *
     * Find the maximum number of glasses of mango shakes you can make with A mangoes and B slices of mangoes initially.
     */
    private static int q4(int A, int B) {
        int slices = A * 3;
        slices += B;
        return slices/2;
    }

    /*
    q5: How many elements are there between [135, 246] (inclusive of them) ?

     ANS: [135, 246] -> 246-135+1 => 112
    */

    /**
     * You are given an integer A. You need to print all the Armstrong Numbers between 1 to A.
     * <p>
     * If sum of cubes of each digit of the number is equal to the number itself, then the number is
     * called an Armstrong number.
     * <p>
     * For example, 153 = ( 1 * 1 * 1 ) + ( 5 * 5 * 5 ) + ( 3 * 3 * 3 ).
     */
    private static int[] q6(int A) {
        ArrayList<Integer> l = new ArrayList<Integer>();
        for (int i = 1; i <= A; i++) {
            int temp = i, sum = 0;
            while (temp > 0) {
                int dig = temp % 10;
                sum += (dig * dig * dig);
                temp /= 10;
            }
            if (sum == i) {
                l.add(i);
            }
        }
        int[] array = new int[l.size()];
        for (int i = 0; i < l.size(); i++) {
            array[i] = l.get(i);
        }
        return array;
    }

    /*
    q7: What is the number of times we need to divide N by 2 till it reaches 1 ?

    ANS: floor(logN)
    */
}