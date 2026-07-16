package com.kritsn.ivs.yulu;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 07, 2025
 */
class Yulu {

    /**
     * return a string with character followed by count of it's consecutive occurence.
     *
     * e.g.
     * Input: aaabccadd
     * output: a3bb1c2a1d2
     */
    public static void convert(String s) {
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count += 1;
            } else {
                System.out.print(s.charAt(i) + "" + count);
                count = 1;
            }

        }

    }

    public static void main(String[] args) {
        convert("aaabccaadd");
    }
}
