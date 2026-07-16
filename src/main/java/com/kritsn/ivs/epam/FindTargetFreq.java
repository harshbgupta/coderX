package com.kritsn.ivs.epam;

import java.util.HashMap;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 23, 2025
 */

public class FindTargetFreq {

    public static void main(String[] args) {
        String s = "abcba";
        String target = "abc";
        int freq = findTargetFreq(s, target);
        System.out.println(freq);
    }

    private static int findTargetFreq(String s, String target) {
        int wordFreq = Integer.MAX_VALUE;
        HashMap<Character, Integer> mapForString = hasMapFreq(s);
        HashMap<Character, Integer> mapForTarget = hasMapFreq(target);

        for (char c : mapForTarget.keySet()) {
            if (!mapForString.containsKey(c)) {
                return 0;
            } else {
                int freq = mapForString.get(c);
                int targetFreq = mapForTarget.get(c);
                int ans = Integer.MAX_VALUE;
                if (freq >= targetFreq) {
//                    ans += freq - targetFreq;
                    ans = freq / targetFreq;
                }
                wordFreq = Math.min(wordFreq, ans);
            }

        }
        return wordFreq;
    }

    private static HashMap<Character, Integer> hasMapFreq(String s) {
        HashMap<Character, Integer> mapForString = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mapForString.containsKey(c)) {
                mapForString.put(c, mapForString.get(c) + 1);
            } else {
                mapForString.put(c, 1);
            }
        }
        return mapForString;
    }
}
