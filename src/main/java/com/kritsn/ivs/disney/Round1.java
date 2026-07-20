package com.kritsn.ivs.disney;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 02, 2026
 */

public class Round1 {

    public static void main(String[] args) {
        //Ques 1, Given an array of strings words and two different strings that already exist in the array
        // word1 and word2, return the shortest distance between these two words in the list.
        //i.e. min distance is 5 between 'fox' & 'dog'
        //i.e. min distance is 3 between 'quick' & 'lazy'. once word before quick and 2 after lazy (including lazy)
        String[] words = {"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        String word1 = "fox", word2 = "dog";
        int minDistance = minDistanceInCircularArray(words, word1, word2);
        System.out.printf("Min distance between %s and %s is %d%n", word1, word2, minDistance);
        System.out.println("------\n");

        // group anagram in arrays
        // String[] words = ["listen", "art","silent", "live", "enlist", "rat", "tar", "eat","evil", "vile","tea","ate"]
        //Expected Output: [[listen, silent, enlist], [rat,tar,art], [evil, vile,live],[eat,tea,ate]]
        String[] anagrams = {"listen", "art", "silent", "live", "enlist", "rat", "tar", "eat", "evil", "vile", "tea", "ate"};
        Map<String, List<String>> result = groupByAnagram(anagrams);
        System.out.println(result.values());

    }


    private static int minDistanceInCircularArray(String[] words, String word1, String word2) {
        int pos1 = 0, pos2 = 0;
        int distance1 = 0, distance2 = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                pos1 = i;
            }
            if (words[i].equals(word2)) {
                pos2 = i;
            }
            if (pos1 != 0 && pos2 != 0) {
                if (pos1 < pos2) {
                    distance1 = pos2 - pos1;
                    distance2 = pos1 + words.length - pos2;
                } else {
                    distance1 = pos1 - pos2;
                    distance2 = pos2 + words.length - pos1;
                }
            }
        }
        return Math.min(distance1, distance2);
    }


    private static Map<String, List<String>> groupByAnagram(String[] anagrams) {
        Map<String, List<String>> map = Arrays.stream(anagrams).collect(
                Collectors.groupingBy(word->
                        sortCharacters(word),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().sorted().collect(Collectors.toList())
                        )
                )
        );
        return map;
    }

    private static String sortCharacters(String words) {
        char[] chars = words.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
