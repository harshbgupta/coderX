package com.kritsn.leetCodeJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * You are given a string s and an array of strings words. All the strings of words are of the same length.
 * A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
 * Return an array of the starting indices of all the concatenated substrings in s.
 */
public class _030SubstringWithConcatenationOfAllWords {

    ///////////////////////////////////////////////////////////////////////////
    // Sliding Window + Frequency Map:
    //
    // We look for substrings of total length = wordLen * wordCount
    // For each index, extract word-sized chunks and validate if they match the word map
    //
    // 🪜 Steps:
    // 1. Build a frequency map of words.
    // 2. Loop over each possible offset (0 to wordLen-1) to support alignment.
    // 3. Within that, slide a window and count frequencies of words within it.
    // 4. If matched, add the starting index to result.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n * m) where n = length of s, m = length of each word.
    // Space Complexity: O(k) where k = number of words in `words`.
    ///////////////////////////////////////////////////////////////////////////
    List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words.length == 0 || s.isEmpty()) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) return result;

        // Build frequency map of the words
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // Try every offset from 0 to wordLen - 1
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int count = 0;
            Map<String, Integer> windowMap = new HashMap<>();

            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
                String word = s.substring(j, j + wordLen);

                if (wordMap.containsKey(word)) {
                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;

                    // Shrink window if frequency exceeds desired count
                    while (windowMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // If all words matched, store the index
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Reset window if word not found
                    windowMap.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        _030SubstringWithConcatenationOfAllWords solver = new _030SubstringWithConcatenationOfAllWords();

        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo", "bar"};
        System.out.println("Test Case 1: s = \"" + s1 + "\", words = " + java.util.Arrays.toString(words1) + " -> Indices = " + solver.findSubstring(s1, words1)); // Expected: [0, 9]

        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word", "good", "best", "word"};
        System.out.println("Test Case 2: s = \"" + s2 + "\", words = " + java.util.Arrays.toString(words2) + " -> Indices = " + solver.findSubstring(s2, words2)); // Expected: []

        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar", "foo", "the"};
        System.out.println("Test Case 3: s = \"" + s3 + "\", words = " + java.util.Arrays.toString(words3) + " -> Indices = " + solver.findSubstring(s3, words3)); // Expected: [6,9,12]

        String s4 = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        String[] words4 = {"fooo", "barr", "wing", "ding", "wing"};
        System.out.println("Test Case 4: s = \"" + s4 + "\", words = " + java.util.Arrays.toString(words4) + " -> Indices = " + solver.findSubstring(s4, words4)); // Expected: [13]

        String s5 = "aaaaaaaaaaaaaa";
        String[] words5 = {"aa", "aa"};
        System.out.println("Test Case 5: s = \"" + s5 + "\", words = " + java.util.Arrays.toString(words5) + " -> Indices = " + solver.findSubstring(s5, words5)); // Expected: [0,1,2,3,4,5,6,7,8,9,10]
    }
}
