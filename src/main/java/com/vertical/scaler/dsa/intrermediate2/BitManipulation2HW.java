package com.vertical.scaler.dsa.intrermediate2;

public class BitManipulation2HW {

    public static void main(String[] args) {

    }

    /**
     * You are given two integers A and B.
     * If B-th bit in A is set, make it unset
     * If B-th bit in A is unset, make it set
     * Return the updated A value
     * <p>
     * TC:
     * SC:
     */
    private static int q1ToggleBit(int A, int B) {
        int num = 1 << B;
        return A ^ num;
    }


    /**
     * Given an integer A. Unset B bits from the right of A in binary.
     * For eg:-
     * A = 93, B = 4
     * A in binary = 1011101
     * A should become = 1010000 = 80. Therefore return 80.
     * TC:
     * SC:
     */
    private static long q2UnsetBBits(long A, int B) {
        A = A >> B; //making right shit till B digits

        return A << B; //making left shit till B digits so they all becomes 0
    }

    /**
     * Alex and Sam are good friends. Alex is doing a lot of programming these days. He has set a target
     * score of A for himself.
     * Initially, Alex's score was zero. Alex can double his score by doing a question, or Alex can seek help from
     * Sam for doing questions that will contribute 1 to Alex's score. Alex wants his score to be precisely A. Also,
     * he does not want to take much help from Sam.
     * <p>
     * Find and return the minimum number of times Alex needs to take help from Sam to achieve a score of A.
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * TC:
     * SC:
     */
    private static int q3solve(int A) {
        /*
        Claim :The number of times we would require help from sam is the number of bits that are set in A.

        Lets try to build an intuition for this. Instead of going from 0 to A , we will go in the reverse direction
        i.e. from A to 0.
        First initialise a cnt variable to 0 which is the number of times we took help from sam.Now we would follow
        this approach until A becomes 0.

        If A is an even number we can divide it by 2
        otherwise if A is an odd number we can subtract 1 from it and increment the cnt.

        Since every time we divide by 2 if its an even number , it is same as doing a left shift. The number of
        times A would become odd is the number of set bits of A.
        Lets take an example to make this more clear:

        A=17
        0) 17 ,cnt=0
        1) 17 -> 16 , cnt=1
        2) 16 -> 8 , cnt=1
        3) 8 -> 4 , cnt=1
        4) 4 -> 2 , cnt=1
        5) 2 -> 1 , cnt=1
        6) 1 -> 0 , cnt=2

        binary representation of 17 = 10001 , and we can see that the number of set bits is the same as cnt that we
        obtained in the above approach.
        Therefore it is enough to just find the number of set bits of A.
        We can do this as follows:
        */

        int ans = 0;
        while (A > 0) {
            if ((A & 1) > 0) {
                //odd
                ++ans;
            }
            A >>= 1; //=> A = A >> 1
        }
        return ans;
    }


    /**
     * TC:
     * SC:
     */
    private static int q4solve(int A) {
        /*
        taking opposite,
        let's say today is last day and we'll go back in past (reverse process)
        today we have A or org stash, let's store it originalVar called org
        yesterday org/2 means A = A>>1
        day before yesterday org/4 means A = A>>1  and so on
        utill we get A beccome 1 and after again right sift A become 0 and loop breaks
        */
        int counter = 0;
        while (A > 0) {
            //checking odd this will on first day when A = 1
            if ((A & 1) > 0) {
                //odd
                ++counter;
            }
            //A divides itself by 2 means A>>1
            A >>= 1; //=> A = A >> 1
        }
        return counter;

    }

    /**
     * Given an integer A, find and return the Ath magic number.
     * <p>
     * A magic number is defined as a number that can be expressed as a power of 5 or a sum of unique powers of 5.
     * <p>
     * First few magic numbers are 5, 25, 30(5 + 25), 125, 130(125 + 5), ….
     * TC:
     * SC:
     */
    private static int q5solve(int A){
        int ans = 0, x = 1;
        // converting to binary representation
        // then making to base 5; 11 = 1011 -> 5^3 + 0 + 5^1 + 5^0
        while(A > 0) {
            x *= 5;
            if(A % 2 == 1)
                ans += x;
            A /= 2;
        }
        return ans;
    }

}
