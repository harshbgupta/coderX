package com.kritsn.ques;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 11, 2025
 */

public class Intersection {

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 4, 5, 3, 7};
        int[] b = {1, 33, 4, 37};
        int[] ans = getTransactionArray(a, b);
        Arrays.stream(ans).forEach(System.out::println);
    }

    public static int[] getTransactionArray(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;

        Arrays.sort(a);
        Arrays.sort(b);

        int aPointer = 0, bPointer = 0;
        List<Integer> ans = new ArrayList<>();

        while (aPointer < n && bPointer < m) {
            if(a[aPointer] < b[bPointer]){
                aPointer++;
            } else if (a[aPointer]>b[bPointer]) {
                bPointer++;
            } else {
                //equals
                if(!ans.contains(a[aPointer])) {
                    ans.add(a[aPointer]);
                }
                aPointer++;
                bPointer++;
            }

        }
        return ans.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static void printArray(int[] arr) {
        Arrays.stream(arr).forEach(System.out::println);
    }
}
