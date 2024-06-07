package com.vertical.scaler.javaRefresher;

import java.util.Scanner;

public class Loop {

    public static void main(String[] args) {
        quesFirstAndLastDigit();
//        countFactors();
//        quesPerfectNumber();
    }

    /**
     * Given an integer N in the input, you need to print the count of it's factors.
     */
    private static void countFactors() {
        System.out.println("Please enter the input...");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int count = 0;
        for (int i = 1; i <= number && number != 0; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        System.out.print(count);
    }

    /**
     *
     */
    public static void quesFirstAndLastDigit() {
        System.out.println("Please enter the input...");
        Scanner scanner = new Scanner(System.in);
        int inputTimes = scanner.nextInt();

        for (int i = 0; i < inputTimes; i++) {
            System.out.println("Please enter the input...");
            long number = scanner.nextInt();
            getFirstAndLastNumber(number);
        }
    }

    private static void getFirstAndLastNumber(long number) {
        int numberOfDigits = digitsOfNumber(number);
        int divider = (int) Math.pow(10, numberOfDigits - 1);
        if (numberOfDigits ==1){
            System.out.print(number + " " + number);
        } else {
            String ans = "";
            for (int i = 0; divider != 0; i++) {
                int digit = (int) (number / divider);
                number = number % divider;
                divider = divider / 10;
                if (i == 0 || divider == 0) {
                    ans += (digit + " ");
                }
            }
            System.out.print(ans.trim());
        }
        System.out.println();
    }

    private static int digitsOfNumber(long number) {
        int count = 0;
        if (number == 0){
            return 1;
        }
        while (number != 0) {
            // num = num/10
            number /= 10;
            ++count;
        }
        return count;
    }

    private static void quesPerfectNumber(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter the input...");
        int n = scn.nextInt();

        for( int i=1;i<=n ;i++){
            long sum = 0;
            System.out.println("Please enter the input...");
            long a = scn.nextLong();
            for(int j=1;j<a ;j++){
                if(a%j ==0){
                    sum += j;
                }
            }
            if(sum == a){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

}
