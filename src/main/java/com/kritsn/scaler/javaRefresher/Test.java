package com.kritsn.scaler.javaRefresher;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        q(args);
//        q1(args);
//        q2(args);
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(Integer.MAX_VALUE);
    }

    public static void q(String[] args) {
        Scanner scn = new Scanner(System.in);
//        DecimalFormat df = new DecimalFormat("0.00");
        int a = scn.nextInt();
        int b = scn.nextInt();
        int c = scn.nextInt();
        int d = scn.nextInt();
        int e = scn.nextInt();

        double sum = a+b+c+d+e;
        double f = (sum)/5;
        System.out.printf("%.2f",f);
//        System.out.println(df.format(f));
    }


    public static void q1(String[] args) {
        //max value of int = 2147483647 --> 10 digits
        //min value of int = -2147483648 --> 10 digits
        int a = 100000;
        int b = 400000;
        long c = (long)(a*b); // a*b = 40000000000 --> 11 digits
        System.out.println(c);
    }

    public static void q2(String[] args) {
        //max value of int = 2147483647 --> 10 digits
        //min value of int = -2147483648 --> 10 digits
        int a = 100000;
        int b = 400000;
        long c = (long)a*b; // a*b = 40000000000 --> 11 digits
        System.out.println(c);
    }
}
