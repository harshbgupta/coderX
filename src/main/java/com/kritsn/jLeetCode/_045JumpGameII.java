package com.kritsn.jLeetCode;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 06, 2025
 */

public class _045JumpGameII {

    private int minJump(int[] nums) {
        if (nums.length == 1) return 0;

        int range = 0, lastIndex = 0, totalJump = 0;

        for (int i = 0; i < nums.length-1; i++) {
            range = Math.max(range, i + nums[i]);

            if (i == lastIndex) {
                lastIndex = range;
                totalJump++;
            }

            //not required, but not wrong solution
//            if (range >= nums.length - 1) return totalJump+1;
        }
        return totalJump;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        _045JumpGameII jumpGameII = new _045JumpGameII();
        System.out.println("total jumps: "+jumpGameII.minJump(nums));
    }
}
