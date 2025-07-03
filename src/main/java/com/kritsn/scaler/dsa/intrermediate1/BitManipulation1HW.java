package com.kritsn.scaler.dsa.intrermediate1;

public class BitManipulation1HW {
    public static void main(String[] args) {
        main(21l);
    }

    /**
     * Given two integers A and B. Find the minimum value (A ⊕ X) + (B ⊕ X) that can be achieved for any X.
     *
     * where P ⊕ Q is the bitwise XOR operation of the two numbers P and Q.
     * TC: O(1)
     * SC:O(1)
     */
    private static int q1(int A, int B) {
        //hint
        /**
         * We can choose any X. So lets try to choose optimally. Say for any ith bit in binary values of A and B,
         * the bit is set for both A and B.
         * Then we can also set it in X such that XOR with both becomes 0.
         * Similarly, if for both A and B the bit was unset, we unset it for X as well. XOR of that bit remains 0.
         * Now try to think if the bit is set for one and unset for other what will X and our result be.
         *
         * That's right doesn't matter if the bit is set or unset that bit will be added to our answer as
         * either A ^ X != 0 or B ^ X != 0 for that bit.
         * Now did you take the observation? If we are adding a bit to out answer if that bit is set for one
         * number and unset for another,
         * then it is A ^ B operation itself. So A ^ B is our answer.
         */


        //Hint study

        /*Case 1*/
        //Let's say that the ith position of A and B are 0 and 0
        //den choose X such a way that A^X^B should be minimum (0)
        // so it 0 ^ X 0 int this case X can not be 1 coz if X = 1 den 0^1^0 =1 (not minimum)
        // so we can take x =0 so 0^0^0 = 0
        // OBSERVATION: so if u see x should be 0, and it is xor if ith position of A and B is the value of x
        // so here in this x =A ^ B (check here ith position of A & b were 0 and 0 so 0^0 = 0 which is X)

        /*Case 2*/
        //Let's say that the ith position of A and B are 1 and 1
        //den choose X such a way that A^X^B should be minimum (0)
        // so it 1 ^ X 1 int this case X can not be 1 coz if X = 1 den (1^1)^1 = 0^1 = 1(not minimum)
        // so we can take x =0 so (0^0)^0 = 0^0 =0
        // OBSERVATION: so if u see x should be 0, and it is xor if ith position of A and B is the value of x
        // so here in this x =A ^ B (check here ith position of A & b were 1 and 1 so 1^1 = 0 which is X)

        /*Case 3*/
        //Let's say that the ith position of A and B are 1 and 0 (or 0 and 1)
        //den choose X such a way that A^X^B should be minimum (0)
        // so it 1 ^ X 1 int this case X can not be 1 coz if X = 1 den (1^0)^0 = 1^1 = 0(minimum)
        // so we can take x =0 so (1^1)^0 = 0^0 =0
        // OBSERVATION: so if u see x can be 0 or 1 both, and it is xor if ith position of A and B is the value of x
        // so here in this x =A ^ B (check here ith position of A & b were 0 and 1 so 0^1 = 1 which is X)

        //////// So final OBSERVATION the value of x is A^B so return A^B
        return A ^ B;
    }

    
    /**
     * You have an array A with N elements. We have two types of operation available on this array :
     * We can split an element B into two elements, C and D, such that B = C + D.
     * We can merge two elements, P and Q, to one element, R, such that R = P ^ Q i.e., XOR of P and Q.
     * You have to determine whether it is possible to convert array A to size 1, containing a single
     * element equal to 0 after several splits and/or merge?
     *
     * TC: 
     * SC: 
     */
    private static void q2(){
        Long A =43l;
        int i =3;
        boolean b = A % i == 0;

    }

    public static int main(Long A) {
        for (int i=2; i<A;i++){
            if (A%i==0)
                return 0;
        }
        return 1;
    }
    
    /**
     * //TODO: DID NOT UNDERSTOOD THE CODE RAISED
     * Given an array B of length A with elements 1 or 0. Find the number of subarrays with bitwise OR 1.
     *
     * TC: 
     * SC: 
     */
    private static long q3Solve(int A, int[] B){
        int last=0;
        long ans=0;
        for(int i=0;i<A;i++){
            if(B[i]==1)last=i+1;
            ans += last;
        }
        return ans;
    }
    
}
