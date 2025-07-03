package com.kritsn.scaler.javaRefresher;

import java.util.Scanner;

public class StringsHW {
    public static void main(String[] args) {

    }

    /**
     * Mr. Jim likes playing with strings a lot, so Mrs. Pam gifted him a string A which has some very unique
     * magical powers. The string consists of lowercase latin letters 'p','a' and 'm' only.
     *
     * In one wish, Mr Jim can perform one of the following operations:
     * Remove exactly one occurrence of 'p' and exactly one occurrence of 'm', from anywhere inside string A.
     * Remove exactly one occurrence of 'a' and exactly one occurrence of 'm', from anywhere inside string A.
     * Now Mr. Jim wants to know if after some number of wishes is it possible to make string A empty.
     *
     * Return 1 if it is possible to make string A empty after some number of operations otherwise Return 0.
     */
    public int q1(String A) {
        int p = 0, a = 0, m = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == 'p')
                p++;
            else if (A.charAt(i) == 'a')
                a++;
            else
                m++;
        }
        if(p + a == m)
            return 1;
        else
            return 0;
    }

    /**
     * Shivam has a string A of size N containing only lowercase characters. He will update the string by inserting a
     * number after every char which will be the index of that char.
     *
     * For E.g.: if string is azd then it will be updated as a1z26d4 since index of a is 1, of z is 26 and of d is 4.
     *
     * You have to tell how the string will look like afterwards,
     */
    public String q2(String A) {
        String ans="";
        for(int i=0; i<A.length(); i++) {
            ans = ans + A.charAt(i) + Integer.toString((int)A.charAt(i)-(int)'a'+1);
        }
        return ans;
    }

    /**
     * You will be given a constant string (A).
     * <p>
     * You have to return another copy of that input string after removing last character from it.
     */
    public static String q3(final String A) {
        String s = "";
        for (int i = 0; i < A.length() - 1; i++) {
            s = s + A.charAt(i);
        }
        return s;
    }

    /**
     * Write a program to input T strings (S) from user and print count of vowels and consonants in it.
     */
    public static void q4(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        for (int i = 0; i < t; i++) {
            String s = scn.next();
            int vowels = 0;
            int conns = 0;
            for (int j = 0; j < s.length(); j++) {
                char a = s.charAt(j);
                if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u') vowels++;
                else conns++;
            }
            System.out.println(vowels + " " + conns);
        }
    }

    /**
     * convert given string into lower case
     */
    public String q5(String A) {
        return A.toLowerCase();
    }

}
