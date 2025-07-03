package com.kritsn.scaler.javaRefresher;

import java.util.Scanner;

public class Func {
    public static void main(String[] args) {
        q1SphereVolume(args);
//        q2Sum(args);
//        q3MangoShake(args);
//        q4Chocolate(args);
        main(args);
    }

    /**
     * You are given a positive integer A denoting the radius of a sphere. You have to calculate the volume of the
     * sphere.
     * <p>
     * Volume of a sphere having radius R is given by (4 * π * R3) / 3.
     * <p>
     * NOTE: Since, the answer can be a real number, you have to return the ceil value of the volume. Ceil value of a
     * real number X is the smallest integer that is greater than or equal to X.
     *
     * @param args
     */
    public static void q1SphereVolume(String[] args) {
        Scanner scn = new Scanner(System.in);
        int radius = scn.nextInt();
        System.out.println(sphereVolume(radius));
    }

    public static int sphereVolume(int radius) {
        double volume = 4 * Math.acos(-1) * Math.pow(radius, 3) / 3.0;
        return (int) Math.ceil(volume);
    }

    /**
     * you are given a positive integer A. You have to print the sum of all even numbers in the range [1, A].
     * @param args
     */
    public static void q2Sum(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0)
                sum += i;
        }
        System.out.println(sum);
    }

    /**
     *Alex is fond of chocolates. He has initially A rupees and a single chocolate costs B rupees.
     *
     * But too many chocolates are not good for health, so Alex will not buy more than C chocolates.
     *
     * Find the number of chocolates Alex will buy with the money he has.
     * @param args
     */
    public static void q3MangoShake(String[] args) {
        Scanner scn = new Scanner(System.in);
        long a = scn.nextLong();
        long b = scn.nextLong();
        long slices = a*3 + b;
        int mangoShake = (int) (slices/2);

        System.out.println(mangoShake);
    }

    /**
     * Given two integers A and B. A represents the count of mangoes and B represent the count of slices of mangoes. Mango can be cut into three slices of mangoes. A glass of mango shake can be formed by two slices of mangoes.
     *
     * Find the maximum number of glasses of mango shakes you can make with A mangoes and B slices of mangoes initially.
     * @param args
     */
    public static void q4Chocolate(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        int b = scn.nextInt();
        int c = scn.nextInt();

        int affordableChocolates = a/b;
        if(affordableChocolates>c){
            System.out.println(c);
        } else {
            System.out.println(affordableChocolates);
        }

    }


}
