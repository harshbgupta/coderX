package com.kritsn.leetCodeJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 16, 2025
 */

public class _049GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        _049GroupAnagrams solver = new _049GroupAnagrams();
        List<List<String>> result = solver.groupAnagrams(strs);
        System.out.println(result);
    }

    private List<List<String>> groupAnagrams(String[] strs){
        HashMap<String, List<String>> map = new HashMap<>();

        for(String str: strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedString = new String(charArray);

            map.computeIfAbsent(sortedString, k -> new ArrayList<>()).add(str);
        }
        return map.values().stream().toList();
    }
}
