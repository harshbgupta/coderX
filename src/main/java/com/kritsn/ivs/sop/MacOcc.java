package com.kritsn.ivs.sop;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 29, 2025
 */


/**
 * 00554112233344445
 * <p>
 * most repeated digit
 */
public class MacOcc {

    public static void main(String[] args) {
//        System.out.println("ans: " + maxOcc("00554112233344445"));
        System.out.println("ans: " + maxOcc("00000000055411223334444555"));
    }

    public static int maxOcc(String s) {
        if (s == null || s.isBlank()) return 'a';
        char ans = s.charAt(0);
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            if (count == 0)
                ans = character;
            if (ans == character) {
                count++;
            } else {
                count--;
            }
        }
        return ans - 48;
    }
}
