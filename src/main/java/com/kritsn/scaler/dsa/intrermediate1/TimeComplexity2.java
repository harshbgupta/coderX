package com.kritsn.scaler.dsa.intrermediate1;

public class TimeComplexity2 {

    /*
    q1:
    What is the time complexity of the following code snippet

    int count = 0;
    while (N > 0) {
        count++;
        N /= 3;
    }

    ANS: O(log(N)) {Base 3}
    */

    /*
    q2:
    To measure Time complexity of an algorithm Big O notation is used all following below:

    ANS:
    1. describes limiting behaviour of the function
    2. characterises a function based on growth of function
    3. upper bound on growth rate of the function
    */

    /*
    q3:
    What is the time complexity of the following code snippet

    int i = 1;
    while (i<N) {
        int x =i;
        while (x-->0){
            //O(1) operation
        }
        i++;
    }

    ANS: O(N^2)
    */

    /*
    q4:
    What is the time complexity of the following code snippet

    for(int i=0; i<N;i++){
        for(int j=0; j<N;j++){
            break;
        }
    }

     ANS: O(N)
    */

    /*
    q5:
    What is the time complexity of the following code snippet

    int a = 0;
    for (int i = 0; i < N; i++) {
        for (int j = N; j > i; j--) {
            a = a + i + j;
        }
    }

     ANS: O(N)
     exp:
     Total number of runs = N + (N - 1) + (N - 2) + ... 1 + 0
        = N * (N + 1) / 2
        = 1/2 * N^2 + 1/2 * N
        O(N^2) times. Total number of runs = N + (N - 1) + (N - 2) + ... 1 + 0
        = N * (N + 1) / 2
        = 1/2 * N^2 + 1/2 * N
        O(N^2) times.
    */

    /*
    q6:
    What is the time complexity of the following code snippet

    int i = 0;
    while (i * i < N) {
        for (int j = 0; j <= N; j++) {
            for (int k = 0; k <= N; i++, k++) {
                //O(1) Operation
            }
        }
    }

     ANS: O(N^2)
     Exp: The nested for loop will have time complexity of O(N^2) and as during the loop run it also increments the
     variable i greater sqrt(N) the while will stop after the nested loop stops.Hence final time complexity will be O(N^2)
    */

}
