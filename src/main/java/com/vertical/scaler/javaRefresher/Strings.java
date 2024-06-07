package com.vertical.scaler.javaRefresher;

import java.util.Scanner;

public class Strings {
    public static void main(String[] args) {
//        q1();
        System.out.println(q2("C0929P98NrT"));
    }

    /**
     * Write a program to input an integer T and then each of T lines will have a string (S).
     * <p>
     * You have to print T lines each containing length of input string.
     */
    public static void q1() {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            String s = scanner.next();
            System.out.println(s.length());
        }

    }

    /**
     * You are given a character string A having length N.
     *
     * The string consists of 2 types of characters:
     *
     * - Alphabets : ['a'-'z', 'A'-'Z']
     * - Digits:['0'-'9']
     *
     * You have to tell the count of characters of the maximum occuring character type.
     */
    public static int q2(final String A) {
        int charCount = 0;
        int digitCount = 0;
        for (int i = 0; i < A.length(); i++) {
            if ((A.charAt(i) >= 'a' && A.charAt(i) <= 'z') || (A.charAt(i) >= 'A' && A.charAt(i) <= 'Z')) {
                charCount++;
            } else {
                digitCount++;
            }
        }
        if (charCount >= digitCount) {
            return charCount;
        } else {
            return digitCount;
        }
    }

    /**
     * You are given a character string A, having length N and an integer ASCII code B.
     *
     * You have to tell the leftmost occurrence of the character having ASCII code equal to B, in A or report
     * that it does not exist.
     */
    public int q3(final String A, final int B) {
        for (int i=0;i<A.length();i++){
            if (B==A.charAt(i)){
                return i;
            }
        }
        return -1;
    }

    /**
     * You are given a character string A, having length N and an integer ASCII code B.
     *
     * You have to tell the rightmost occurrence of the character having ASCII code equal to B, in A or report
     * that it does not exist.
     */
    public int q4(final String A, final int B) {
        for (int i=A.length()-1;i>=0;i--){
            if (B==A.charAt(i)){
                return i;
            }
        }
        return -1;
    }

    /**
     * You are given lowercase string (A) and you have to return after converting that in upercase form.
     */
    public String q5(String A) {
        return A.toUpperCase();
    }
}
