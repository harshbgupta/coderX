package com.kritsn;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 10, 2026
 */

public class Test {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("A","B","A","B","A","B","A","C","A","C","A","C");

        Map<String, Long> values = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        Set<Integer> set= values.entrySet().stream().m

    }
}


// Arrays.asList("A","B","A","B","A","B","A","C","A","C","A","C")