package com.kritsn.scaler.javaRefresher;

import java.util.Scanner;

public class FuncHW {
    public static void main(String[] args) {
        q1DecToBin(args);
//        q2BinToDec(args);
//        q3CountOfPrimeNumber(args);
//        q4HCF(args);
        main(args);
    }


    public static void q1DecToBin(String[] args) {
        Scanner scn = new Scanner(System.in);
        int s = scn.nextInt();
        String binaryS = Integer.toBinaryString(s);
        System.out.println(Integer.parseInt(binaryS));
    }

    public static void q1DecToBin_1(String[] args) {
        Scanner scn = new Scanner(System.in);
        int s = scn.nextInt();
        //TODO: Custom Logic
    }

    public static void q2BinToDec(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();

        int pow = 0;
        long dec = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            String c = s.charAt(i) + "";
            if (c.equals("1")) {
                dec = (long) (dec + Math.pow(2, pow));
            }
            pow++;
        }
        System.out.println(dec);
    }

    /**
     * You will be given an integer n. You need to return the count of prime numbers less than or equal to n.
     *
     * @param args
     */
    public static void q3CountOfPrimeNumber(String[] args) {
        Scanner scn = new Scanner(System.in);
        long a = scn.nextLong();
        int count = 0;
        for (long i = 2; i <= a; i++) {
            if (checkPrime(i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean checkPrime(long number) {
        boolean status = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                status = false;
                break;
            }
        }
        return status;
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
    public static void q4HCF(String[] args) {
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
     * Implement a program that takes two positive integers A and B in the input and prints their LCM.
     * <p>
     * Definition of LCM : The Least Common Multiple or LCM of two numbers say A and B, is denoted as LCM (A,B).
     * And the LCM is the smallest or least positive integer that is divisible by both A and B.
     *
     * @param args
     */
    public static void q5LCM(String[] args) {
        Scanner scn = new Scanner(System.in);
        long n1 = scn.nextLong();
        long n2 = scn.nextLong();
        long lcm = Math.max(n1, n2);

        // Always true
        while (true) {
            if (lcm % n1 == 0 && lcm % n2 == 0) {
                System.out.print(lcm);
                break;
            }
            ++lcm;
        }
    }
}
