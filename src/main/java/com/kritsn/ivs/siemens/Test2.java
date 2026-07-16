package com.kritsn.ivs.siemens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 08, 2025
 */

public class Test2 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 5, 5, 4, 4, 5};

        Arrays.sort(nums); //-> O(log N)
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return;
        }
        int prev = nums[0];
        list.add(nums[0]);

        //TC: O(N)
        //SC: O(N)
        for (int i = 1; i < nums.length; i++) {
            if (prev != nums[i]) {
                list.add(nums[i]);
                prev = nums[i];
            }
        }



        //TC: O(N^2) -> Brute force approach
        //SC: O(N)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] != nums[j]) {
                    list.add(nums[i]);
                }
            }
        }

    }
}
