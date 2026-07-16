package com.kritsn.ivs.epam;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 23, 2025
 */

public class LongestPrefix {
    public static void main(String[] args) {

    }

    private static String longestPrefix(String[] str) {

        String longestPrefix = "";
        for (int i = 1; i < str.length; i++) {
//            longestPrefix = longestPrefixInGivenTwoString(str.substring(0, i), str.substring(i));
        }
        return longestPrefix;
    }

    private static String longestPrefixInGivenTwoString(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            if (i < str2.length() && str1.charAt(i) == str2.charAt(i)) {
                sb.append(str1.charAt(i));
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }

}
