package com.vertical.scaler.javaRefresher;

import java.util.Scanner;

public class TransitionTest {
    public static void main(String[] args) {
//        q1();
//        q2();
//        q3();
    }

    /**
     *find out max and min number from array
     */
    private static void q1(){
        Scanner scn = new Scanner(System.in);
        int[] arr = getArray(scn);
        int max = arr[0];
        int min = arr[0];
        for (int i=1; i<arr.length;i++){
            if(arr[i]< min)
                min = arr[i];
            if(arr[i]> max)
                max = arr[i];
        }
        System.out.println(max+" "+min);
    }

    private static int[] getArray(Scanner scn){
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i=0; i<n;i++){
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    /**
     * sum of digits
     */
    private static void q2(){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for(int i=0;i<n;i++){
            long num = scn.nextLong();
            int sum = 0;
            while(num>0){
                sum += (num %10);
                num = num/10;
            }
            System.out.println(sum);
        }
    }

    /**
     * Count Upper case in a string
     */
    private static void q3(){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i=0; i<n;i++){
            String s = scn.next();
            countUpperCase(s);
        }
    }
    private static void countUpperCase(String s){
        int count = 0;
        if(s==null){
            System.out.println(count);
            return;
        }

        for(int i =0; i<s.length();i++){
            char c = s.charAt(i);
            if(c<=90 && c>=65){
                count++;
            }
        }
        System.out.println(count);
    }
}
