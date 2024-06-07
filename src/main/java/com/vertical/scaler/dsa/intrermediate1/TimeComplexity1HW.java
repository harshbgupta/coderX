package com.vertical.scaler.dsa.intrermediate1;

public class TimeComplexity1HW {

    /*
    q1:
    What is the time complexity of the following code snippet

    int func(int n){
        int s = 0;
        for(int i = 1 ; i*i*i <= n ; i++){
            s = s + i;
        }
        return s;
    }

    ANS: O(n^(1/3))
    */

    /*
    q2:
    What is the time complexity of the following code snippet

    int func(int n){
        int ans = 0;
        for(int i = 1 ; i <= n ; i++){
            ans+=i*i;
        }
        return ans;
    }

    ANS: O(N)
    */

    /*
    q3:
    What is the time complexity of the following code snippet

    private static void q3(int n) {
        while (n>0){
            n++;
            n -=2;
        }
    }

    ANS: O(N)
    */


    /*
    q4:
    What is the time complexity of the following code snippet

    for (int i=n;i>0;i /= 2){
        if (i%2==0){
            for (int j=1;j<=n*n;j += 2){
                //O(1)
            }
        }
    }

    ANS: O(n^2 logn)
    exp:
    The first loop runs for log2n operations. -> O(log n)
    The second loops runs for (n * n) / 2 operations. -> O(n2)
    Therefore, the time complexity of the complete solution is O(n2 log n).
    */

    /*
    q5:
    What is the time complexity of the following code snippet

    for (int j=1;j<=n;j ++){
        for (int j=i-1;j>o;j ++){
            //O(1)
        }
    }


    ANS: Code will run indefinitely
    exp:
    inner loop j will always be a grate than 0 for i =2 and above
    */

    /*
    q6:
    If for an algorithm time complexity is given by O(1) then complexity of it is:
        for (int j=1;j<=n;j ++){
             for (int j=1;j<n && j>i;j ++){
             }
        }
     ANS: O(N)
     exp:Since the inner loop condition is the j should be greater than i and as j is initialised with i , the inner loop will stop then and there.
    Hence O(N) time complexity
     */

    /*
    q7:
    If for an algorithm time complexity is given by O(1) then complexity of it is:

     ANS: Constant
    */

    /*
    q8:
    If for an algorithm time complexity is given by O(log2n) then complexity will:

     ANS: Not Polynomial, not exponential, not constant (ans None of these)
    */

    /*
    q9:
    If for an algorithm time complexity is given by O(n) then complexity of it is:

     ANS: linear
    */

    /*
    q10:
    If for an algorithm time complexity is given by O((3/2)^n) then complexity will:

     ANS: exponential
    */

    /*
    q11:
    What is the time complexity of the following code snippet
        //TODO IMP
        for(int i = 1 , j = 1 ; j <= n ; i++){
        print(i + j);
        if(i % n == 0){
            j++;
        }

    ANS: O(n^2)
    */
}