package com.kritsn.leetCodeJava;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jun 15, 2026
 */
/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Given a Roman numeral, convert it to an integer.
 */
public class _013RomanToInteger {

    ///////////////////////////////////////////////////////////////////////////
    // Greedy Left-to-Right Scan:
    // We traverse the string from left to right.
    // - If current symbol < next symbol: subtract its value.
    // - Else: add its value.
    //
    // 🪜 Steps:
    // 1. Create a map of Roman symbol to integer value.
    // 2. Loop through each character:
    //    - If current < next → subtract
    //    - Else → add
    // 3. Return the final result.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — where n is the length of the Roman string.
    // Space Complexity: O(1) — fixed map of 7 Roman symbols.
    ///////////////////////////////////////////////////////////////////////////
    public int romanToInt(String s) {
        Map<Character, Integer> values = new HashMap();
        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int currentVal = values.get(ch) == null ? 0 : values.get(ch);
            int nextVal = (i + 1 < s.length()) ? values.get(s.charAt(i + 1)) : 0;

            if (currentVal < nextVal) {
                res -= currentVal;
            } else {
                res += currentVal;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new _013RomanToInteger().romanToInt("III") + " The Real answer is: " + 3);
        System.out.println(new _013RomanToInteger().romanToInt("IV") + " The Real answer is: " + 4);
        System.out.println(new _013RomanToInteger().romanToInt("IX") + " The Real answer is: " + 9);
        System.out.println(new _013RomanToInteger().romanToInt("LVIII") + " The Real answer is: " + 3);
        System.out.println(new _013RomanToInteger().romanToInt("XC") + " The Real answer is: " + 90);
        System.out.println(new _013RomanToInteger().romanToInt("MCMXCIV") + " The Real  answer is: " + 1994);
    }
}
