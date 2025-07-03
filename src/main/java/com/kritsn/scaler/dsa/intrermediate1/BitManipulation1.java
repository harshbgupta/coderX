package com.kritsn.scaler.dsa.intrermediate1;

public class BitManipulation1 {

    public static void main(String[] args) {
        System.out.println(q8(10, 4));

    }

    /**
     * You are given a number A. You are also given a base B. A is a number on base B.
     * You are required to convert the number A into its corresponding value in decimal number system.You are given a
     * number A. You are also given a base B. A is a number on base B.
     * You are required to convert the number A into its corresponding value in decimal number system.
     */
    public static int q7(int A, int B) {
        int counter = 0, ans = 0;
        while (A > 0) {
            int digit = A % 10;
            ans = (int) (ans + (digit * Math.pow(B, counter)));
            A = A / 10;
            counter++;
        }
        return ans;
    }

    /**
     * Given a decimal number A and base B.
     * You are required to change the decimal number A into the corresponding value in base B and return that.
     */
    public static int q8(int A, int B) {
        //checking the special condition if A=0;
        if (A == 0)
            return 0;
        StringBuilder ans = new StringBuilder();
        while (A > 0) {
            int digit = A % B;
            // putting the digit always on first place (0th index)
            ans.insert(0, digit);
            A = A / B;
        }
        return Integer.parseInt(ans.toString());
    }

    /**
     * Given an array of integers A, every element appears twice except for one. Find that integer that occurs once.
     *
     * NOTE: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     * TC: O(N)
     * SC: O(1)
     */
    public int q9SingleNumber(final int[] A) {
        //using following concepts
        // 0 ^ B = B        => same 0 & ele's XOR = ele
        // and A ^ A = 0      => same ele's XOR = 0
        // and A ^ B ^ A = B    => (A ^ A) ^ B = 0 ^ B = 0
        int num = 0;
        for (int val : A) {
            // xor of all the elements of the array
            num ^= val;
        }
        return num;
    }
}
