package com.vertical.scaler.dsa.intrermediate2;

public class BitManipulation2 {

    public static void main(String[] args) {
//        System.out.println(q1(10, 4));
    }

    /**
     * You are given two integers A and B.
     * -> Return 1 if B-th bit in A is set
     * -> Return 0 if B-th bit in A is unset
     * <p>
     * TC: O(1)
     * SC: O(1)
     */
    private static int q1Solve(int A, int B) {
        //A>>B is shifting the bth bit to the rightest position
        // Once 1st task is done taking the  & operation with 1  which
        // return 1 if the rightest position is 1 else 0
        if ((A >> B & 1) == 1) return 1;
        else return 0;
    }

    /**
     * Write a function that takes an integer and returns the number of 1 bits it has.
     * <p>
     * <p>
     * ITR: only that as times as 1 (set bit) exits in number it could be all
     * digits ot just 1 one or just zero
     * TC: O(logN)
     * SC:
     */
    private static int q2NumSetBits(int A) {
        int count = 0;
        while (A > 0) {
            if ((A & 1) == 1) count++;
            A = A >> 1;
        }
        return count;
    }

    /**
     * Write a function that takes an integer and returns the number of 1 bits it has.
     *
     * ITR: only that as times as 1 (set bit) exits in number it could be all
     * digits ot just 1 one or just zero
     * TC: O(logN)
     * SC:
     */
    private static int q2NumSetBitsMuchOptimised(int A) {
        /*
         a = original number
         b = original number - 1
         after subtracting the number by 1 it flips the bits till rightmost 1( including the right most 1)
         now if we take & operation with original and this new number it'll turn all
         bits zero till second rightmost bit in original number and now replace the original with this one
         we can do the same process, until umber becomes zero

         so in short this process will run only that number of time, one number of bits 1 in the original number
         Ao we just need to return the count

         exp :

         let's say a number             A => 1 0 1 0 0 1 0 0
         let's do A-1,          B = A - 1 => 1 0 1 0 0 0 1 1
         let's do A & B             A & B => 1 0 1 0 0 0 0 0
         repeat the same
        */
        int count = 0;
        while (A > 0) {
            int nMinus1 = A - 1;
            A = A & nMinus1;
            count++;
        }
        return count;
    }

    /**
     * You are given two integers A and B.
     * Set the A-th bit and B-th bit in 0, and return output in decimal.
     *
     * TC: O(1)
     * SC: O(1)
     */
    private static int q3SetBit(int A, int B) {
        int num = (1 << A);
        if(A != B){
            num += (1 << B);
        }
        return num;
    }

    /**
     * You are given two integers A and B.
     * If B-th bit in A is set, make it unset
     * If B-th bit in A is unset, leave it as it is
     * Return the updated A value
     *
     * TC: O(1)
     * SC: O(1)
     */
    private static int q4(int A, int B)  {
        int num = 1 << B;
        if((A & num) > 0){
            A -= num;
        }
        return A;
    }

    private static long binaryToDec(String radixBaseString) {
        return Integer.parseInt(radixBaseString, 2);
    }
}
