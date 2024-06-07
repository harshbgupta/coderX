package com.vertical.scaler.dsa.intrermediate1;

public class TimeComplexity2HW {

    /*
    q1:
    What is the time, space complexity of following code :

        int a = 0, b = 0;
        for (i = 0; i &lt; N; i++) {
            for (j = 0; j &lt; N; j++) {
                a = a + j;
            }
        }
        for (k = 0; k &lt; N; k++) {
            b = b + k;
        }

    ANS:
    Time Complexity: O(N^2)
    Space Complexity: O(1)
    */

    /*
    q2:
    An algorithm consists of two independent piece of code, having complexities f(n) and g(n) respectively. What would be the complexity of the complete algorithm

    ANS:
    MAX( f(n), g(n) )
    */

    /*
    q3:
    What is the time, space complexity of following code :

    public static void fun(int n) {
    int i, j;
        for (i = 1; i * i &lt;= n; i++) {
            for (j = 1; j * j &lt;= i; j += i) {
                //O(1) operation
            }
        }
    }

    ANS: O(√n)
    Ex:The outer loop wil run √n times. The inner loop will run only for j = 1 since 1 + i > √i. Thus, the time complexity is O(√n).
    */

    /*
    q4:
    What is the time, space complexity of following code :

    public void solve(int N) {
        for(int i = 0; i &lt; Math.pow(2,N); i++) {
            int j = i;
            while(j &gt; 0){
                j -= 1;
            }
        }
    }


     ANS: O(N)
     Exp: The outer loops runs from 1 to 2^ N.
    The inner loop runs from i to 0.
    So, worst case TC will be O((2^N)^2) = O(4^N).
    */

    /*
    q5:
    What is the time, space complexity of following code :

    int count = 0;
    for (int i = N; i &gt; 0; i /= 2) {
        for (int j = 0; j &lt; i; j++) {
            count += 1;
        }
    }

     ANS: O(N)
     exp:
     In the first iteration, the j loop runs N times.
    In the second iteration, the j loop runs N / 2 times.
    In the ith iteration, the j loop runs N / 2^i times.
    So, the total number of runs of loop = N + N / 2 + N / 4 + ... 1
    = **N * ( 1 + 1/2 + 1/4 + 1/8 + ... ) < 2 * N**
    */

    /*
    q6:
    What is the time, space complexity of following code :

    for (int i = 3 ; i &lt; n / 3  ;  i += 3) {
        for(int j = 2 ; j &lt; n / 2 ; j += 2){
            // O(1) operation
        }
    }

     ANS: O(N^2)
     Exp: The nested for loop will have time complexity of O(N^2) and as during the loop run it also increments the
     variable i greater sqrt(N) the while will stop after the nested loop stops.Hence final time complexity will be O(N^2)
    */

}
