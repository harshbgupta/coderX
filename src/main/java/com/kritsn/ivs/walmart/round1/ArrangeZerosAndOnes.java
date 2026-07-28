package com.kritsn.ivs.walmart.round1;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 28, 2026
 */

public class ArrangeZerosAndOnes {

    public static void main(String[] args) {
        int [] arr1 = {1,0,1,1,0,0,0,1};
        Arrays.stream(sortArrayZerosOnes(arr1)).forEach(System.out::print);
        int [] arr2 = {0,0,1,1,0,0,0,1};
        System.out.println();
        Arrays.stream(sortArrayZerosOnes(arr2)).forEach(System.out::print);
        int [] arr3 = {1,1,0,0,0,0,0,1};
        System.out.println();
        Arrays.stream(sortArrayZerosOnes(arr3)).forEach(System.out::print);
    }

    public static int[] sortArrayZerosOnes(int[] arr) {
        int left = 0;  // position where next 0 should go

        for (int right = 0; right < arr.length; right++) {
            if (arr[right] == 0) {
                // Found a 0, swap it to left position
                swap(arr, left, right);
                left++;
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
