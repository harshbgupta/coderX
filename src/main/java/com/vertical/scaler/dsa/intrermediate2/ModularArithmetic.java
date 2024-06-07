package com.vertical.scaler.dsa.intrermediate2;

public class ModularArithmetic {
    public static void main(String[] args) {

    }

    /**
     * You are given A, B and C .
     * Calculate the value of (A ^ B) % C => ^ is powwr
     * TC:
     * SC:
     */
    private static int q1PowerWithModules(int A, int B, int C) {
        //(A * B) % M = ((A % M) * (B %M)) % M => Formula
        long temp = 1;
        for (int i = 0; i < B; i++) {
            temp = (temp * A) % C;
        }
        return (int) temp;
    }

    /**
     * You are given a large number in the form of a array A of size N where each element denotes a digit of the number.
     * You are also given a number B. You have to find out the value of A % B and return it.
     * <p>
     * TC:
     * SC:
     */
    private static void q3() {

    }

    /**
     * Given a number in the form of an array A of size N. Each of the digits of the number is represented by A[i].
     * Check if the number is divisible by 3.
     * TC:
     * SC:
     */
    private static int q3DivisibleBy3(int[] A) {
        //sum of digits is divisible 3 den number is divisble by 3
        int digitsSum = 0;
        for (int a : A) {
            digitsSum += a;
        }
        Math.pow(2,3);
        //checking sum id divisible by 3 or not
        if (digitsSum % 3 == 0) {
            return 1;
        } else {
            return 0;
        }

    }
}
