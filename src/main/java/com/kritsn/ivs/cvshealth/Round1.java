package com.kritsn.ivs.cvshealth;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 27, 2026
 */

/*
    find out the most occurrence character from given List Using Stream
    ["A","B","A","B","A","B","A","C","A","C","A","C"]
 */
public class Round1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A","B","A","B","A","B","A","C","A","C","A","C");
        Map.Entry<String, Long> result = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();

        System.out.println(result.getKey() + " -> " + result.getValue()); // A -> 6
    }
}
