package com.vertical.scaler.javaRefresher;

import java.util.Scanner;

public class LoopHW {
    public static void main(String[] args) {
//        qHCF(args);
//        qSumOfDigits2(args);
//        qArmstrongNumbers(args);
//        qIsPrime(args);
        qPalindrome(args);
    }

    /**
     * Write a program to input two integers A & B from user and print their HCF.
     * <p>
     * Definition Of HCF: The HCF(Highest Common Factor) or the GCD(greatest common divisor) of two
     * positive integers happens to be the largest positive integer that divides the numbers without
     * leaving a remainder.
     *
     * @param args
     */
    public static void qHCF(String[] args) {
        Scanner scn = new Scanner(System.in);
        long a = scn.nextLong();
        long b = scn.nextLong();
        long legnth = a;
        if (a <= b) {
            legnth = a;
        } else {
            legnth = b;
        }

        long hcf = 1;
        for (int i = 1; i <= legnth; i++) {
            if (a % i == 0 && b % i == 0) {
                hcf = i;
            }
        }
        System.out.print(hcf);
    }

    /**
     * Mr. Q has a diary in which a lot of numbers are written. He wants to know the sum of digits for every number.
     * Ask Mr. Q about the total numbers written in the diary and then write a code to find the sum of digits for every number.
     * <p>
     * Note : Total different Numbers are T and for every number (let's say N) you need to find Sum of digits.
     *
     * @param args
     */
    public static void qSumOfDigits2(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();

        for (int i = 1; i <= t; i++) {
            long n = scn.nextLong();
            long sum = 0l;
            while (n != 0) {
                sum += n % 10;
                n = n / 10;
            }
            System.out.println(sum);
        }

    }

    /**
     * You are given an integer N you need to print all the Armstrong Numbers between 1 to N.
     * <p>
     * If sum of cubes of each digit of the number is equal to the number itself, then the number is
     * called an Armstrong number.
     * <p>
     * For example, 153 = ( 1 * 1 * 1 ) + ( 5 * 5 * 5 ) + ( 3 * 3 * 3 ).
     *
     * @param args
     */
    public static void qArmstrongNumbers(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 1; i <= n; i++) {
            long sum = qSumOfDigits2(i);
            if (sum == i) {
                System.out.println(i);
            }
        }
    }

    public static Long qSumOfDigits2(long n) {
        long sum = 0l;
        while (n != 0) {
            int digit = (int) (n % 10);
            sum += Math.pow(digit, 3);
            n = n / 10;
        }
        return sum;
    }

    /**
     * You are given an integer N you need to print all the Prime Numbers between 1 and N.
     *
     * @param args
     */
    public static void qIsPrime(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime)
                System.out.println(i);
        }
    }

    public static void qPalindrome(String[] args) {
        Scanner scn = new Scanner(System.in);
        long n = scn.nextLong();
        if (n==reverseNumberAlgo1(n)){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static long reverseNumberAlgo1(long number) {
        long r, sum = 0;
        while (number > 0) {
            r = number % 10;  //getting remainder
            sum = (sum * 10) + r;
            number = number / 10;
        }

        //now sum will be reverse order of the given numbeer
        return  sum;
    }
}
