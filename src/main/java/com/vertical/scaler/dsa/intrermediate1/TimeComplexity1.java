package com.vertical.scaler.dsa.intrermediate1;

public class TimeComplexity1 {
    public static void main(String[] args) {

    }

    /*
    q1:
    What is the time complexity of the following code snippet

    for(int i = 1 ; i <= n ; i+=2){
        print(i);
    }

    ANS: O(n)
    */

    /*
    q2:
    What is the time complexity of the following code snippet

    void solve(int N, int M){
        for(int i = 1 ; i <= N ; i++){
            if(N % i == 0)
                print(i);
        }
        for(int i = 1 ; i <= M ; i++){
            if(M % i == 0)
                print(i);
        }
     }

    ANS: O(N+M)
    */

    /*
    q3:
    What is the time complexity of the following code snippet

    int func(int n){
        int s = 0;
        for(int i = 1 ; i <= 100 ; i++){
            s = s + i;
        }
        return s;
    }

    ANS: O(1)
    */

    /*
    q4:
    What is the time complexity of the following code snippet

    int func(int n){
        int s = 0;
        for(int i = 0 ; i < n ; i = i * 2){
            s = s + i;
        }
        return s;
    }

     ANS: O(∞) => i always is zero 0*2 =0 in every iteration
    */

    /*
    q5:
    What is the time complexity of the following code snippet

    for(int i = 1 ; i <= 100 ; i*=2){
         for(int j = 1 ; j <= n ; j++){
            print(i + j);
         }
     }

     ANS: O(N)
    */

    /*
    q6:
    What is the time complexity of the following code snippet

    for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j <= i ; j++){
                print(i+j);
            }
     }

     ANS: O(N^2)
    */

    /*
    q7:
    What is the time complexity of the following code snippet

    for(int i = 1 ; i <= n ; i*=2){
            for(int j = 1 ; j <= n ; j++){
                print(i + j);
            }
     }

     ANS: O(n logn)
    */

    /*
    q8:
    What is the time complexity of the following code snippet

        int j = 0;
        for(int i = 0 ; i < n ; i++){
            while(j <= i){
                print(i + j);
                j++;
            }
        }

     ANS: O(N)
    */

    /*
    q9:
    What is the time complexity of the following code snippet

    int a = 0, i = N;
    while (i > 0) {
        a += i;
        i /= 2;
    }

     ANS: O(log N) => devide a number by 2 until it becomes 1
    */

    /*
    q10:
    What is the time complexity of the following code snippet
    for(int i = 1 ; i <= n ; i++){
        for(int j = 1 ; j <= 3^i ; j++){
            print(i + j);
        }
    }

     ANS: O(3^n)
    */

}
