package com.kritsn.jLeetCode;

/*
  Copyright © 2025 Kritsn LLP. All rights reserved.
 
  @author Radhey (hr-sh)
  @since Aug 05, 2025
 */

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 * @author Radhey (hr-sh)
 * @since Aug 05, 2025
 */
public class _005LongestPalindromeSubString {

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindromeSunString(String s) {
        if (s == null || s.isEmpty()) return "";
        if (s.length() == 1) return s;

        int start = 0;
        int maxLength = 0;

        for(int i = 0; i < s.length(); i++){
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);

            int currentLength = Integer.max(len1, len2);

            if (currentLength > maxLength){
                maxLength = Integer.max(maxLength, currentLength);
                start = i - (currentLength - 1) / 2;
            }
        }
        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        _005LongestPalindromeSubString solution = new _005LongestPalindromeSubString();
        System.out.println("Longest palindromic substring: " + solution.longestPalindromeSunString("babad"));
    }
}
