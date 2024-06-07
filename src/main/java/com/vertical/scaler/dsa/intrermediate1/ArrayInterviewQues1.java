package com.vertical.scaler.dsa.intrermediate1;

public class ArrayInterviewQues1 {
    public static void main(String[] args) {
//        int [] A = {1, 2, 4, 3};
//        q3(A);
        System.out.println("Answer" + q4(7));
    }

    /**
     * QUES1:
     * One hundred people are standing in a circle in an order 1 to 100.
     *
     * No.1 has a sword. He kills the next person (i.e., no. 2) and gives
     * the sword to the next (i.e., no. 3). All person does the same until only one survives.
     *
     * ANS:
     * Round 1 : 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99
     * Round 2: 1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 41, 45, 49, 53, 57, 61, 65, 69, 73, 77, 81, 85, 89, 93, 97
     * Round 3: 1, 9, 17, 25, 33, 41, 49, 57, 65, 73, 81, 89, 97
     * Round 4: 9, 25, 41, 57, 73, 89
     * Round 5: 9, 41, 73
     * Round 6: 9, 73
     * Round 7: 73
     */


    /**
     * Ques2:
     * Given a binary string A. It is allowed to do at most one swap between any 0 and 1. Find and return the length
     * of the longest consecutive 1’s that can be achieved.
     */
    public static int q2(String s) {
        int[] leftConsecutiveOnes = new int[s.length()];
        int[] rightConsecutiveOnes = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                if (i == 0) {
                    leftConsecutiveOnes[i] = 1;
                } else {
                    leftConsecutiveOnes[i] = leftConsecutiveOnes[i - 1] + 1;
                }
            } else {
                leftConsecutiveOnes[i] = 0;
            }

            int index = s.length() - i - 1;
            if (s.charAt(index) == '1') {
                if (i == 0) {
                    rightConsecutiveOnes[index] = 1;
                } else {

                    rightConsecutiveOnes[index] = rightConsecutiveOnes[index + 1] + 1;
                }
            } else {
                rightConsecutiveOnes[index] = 0;
            }
        }
        Bag.printArray(leftConsecutiveOnes);
        Bag.printArray(rightConsecutiveOnes);

        //gettign total one in string
        int totalOneInString = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1')
                totalOneInString++;
        }


        int cnt = 0;
        int maxConsecutiveOne = 0; //will store max consecutive one in string
        for (int i = 0; i < n; ++i) {
            maxConsecutiveOne = Math.max(maxConsecutiveOne, Math.max(rightConsecutiveOnes[i], leftConsecutiveOnes[i]));
        }

        //getting ans here
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                //sum id sum Of One If We Replace Zero As One
                int sumOfOneIfWeReplaceZeroAsOne = leftConsecutiveOnes[i - 1] + rightConsecutiveOnes[i + 1];

                if (sumOfOneIfWeReplaceZeroAsOne < totalOneInString) {
                    cnt = sumOfOneIfWeReplaceZeroAsOne + 1;
                } else {
                    //
                    cnt = sumOfOneIfWeReplaceZeroAsOne;
                }

                maxConsecutiveOne = Math.max(maxConsecutiveOne, cnt);
                cnt = 0;
            }
        }
        return maxConsecutiveOne;
    }

    /**
     * You are given an array A of N elements. Find the number of triplets i,j and k such that i<j<k and A[i]<A[j]<A[k]
     */
    public static int q3(int[] A) {
        int ans = 0;

        for (int i = 1; i < A.length; i++) {
            int leftCount = 0;
            int rightCount = 0;

            for (int j = 0; j < i; j++) {
                if(A[i]>A[j]){
                    //where i<j and A[i]<A[j]
                    leftCount+=1;
                }
            }

            for (int j = i+1; j <A.length; j++) {
                if(A[i]<A[j]){
                    //where i>j and A[i]>A[j]
                    rightCount+=1;
                }
            }

//            System.out.println("leftCount: "+leftCount);
//            System.out.println("rightCount: "+rightCount);
            ans += (leftCount * rightCount);
//            System.out.println("ans: "+ans);
        }
//        System.out.println("final ans: "+ans);
        return ans;
    }

    public static int q4(int A) {
        int position = q4Helper(A);
        int j = (int) Math.pow(2, position - 1);
        A = A - j;
        A *= 2;
        A += 1;
        return A;
    }

    public static int q4Helper(int n){
        int pos = 0;
        while (n != 0) {
            pos++;
            n = n / 2;
        }
        System.out.println("pow "+pos);
        return pos;
    }
}
