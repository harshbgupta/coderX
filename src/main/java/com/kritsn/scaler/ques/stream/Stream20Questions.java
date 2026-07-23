package com.kritsn.scaler.ques.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 16, 2025
 */

public class Stream20Questions {
    public static void main(String[] args) {
        steam20Questions();
    }

    /**
     *
     *
     * TC:
     * SC:
     */
    private static void steam20Questions() {
        List<Integer> numbers = List.of(5, 3, 9, 1, 7, 9);
        //1. Find the maximum number in a list
        int max = numbers.stream().max(Integer::compare).orElseThrow();
        System.out.println("max: " + max);
        System.out.println("------------------");

        //2. Count even numbers in a list
        long countEvenNumber = numbers.stream().filter(n -> n % 2 == 0).count();
        System.out.println("countEvenNumber: " + countEvenNumber);
        System.out.println("------------------");

        //3. Convert list of strings to uppercase
        List<String> names = List.of("john", "doe", "Amar");
        List<String> namesUppercase = names.stream().map(String::toUpperCase).toList();
        System.out.println("namesUppercase: " + namesUppercase);
        System.out.println("------------------");

        //4. Filter names starting with 'A'
        List<String> namesStartsWithA = names.stream().filter(n -> n.startsWith("A")).toList();
        System.out.println("namesStartsWithA: " + namesStartsWithA);
        System.out.println("------------------");

        //5. Find unique elements in a list
        List<Integer> uniqueNumber = numbers.stream().distinct().toList();
        System.out.println("uniqueNumber: " + uniqueNumber);
        System.out.println("------------------");

        //5.2 Find duplicate elements in a list
        List<Integer> list = List.of(1, 2, 2, 3, 4, 4, 5);
        Set<Integer> seen = new HashSet<>();
        List<Integer> duplicateNumber = list.stream().filter(n -> !seen.add(n)).toList();
        System.out.println("duplicateNumber: " + duplicateNumber);
        System.out.println("------------------");

        //6. Get first 3 elements of a list
        List<Integer> firstThree = numbers.stream().limit(3).toList();
        System.out.println("firstThree: " + firstThree);
        System.out.println("------------------");


        //7. Get skip first 3 elements of a list
        List<Integer> skipThree = numbers.stream().skip(3).toList();
        System.out.println("skipThree: " + skipThree);
        System.out.println("------------------");

        //8. Sum of all numbers in list
        int sumOfAllNumbers = numbers.stream().reduce(0, (a, b) -> a + b);
        int sumOfAllNumbers2 = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("sumOfAllNumbers: " + sumOfAllNumbers);
        System.out.println("sumOfAllNumbers2: " + sumOfAllNumbers2);
        System.out.println("------------------");

        //9. Check if all numbers are even
        Boolean isAllEven = numbers.stream().allMatch(n -> n % 2 == 0);
        System.out.println("isAllEven: " + isAllEven);
        System.out.println("------------------");

        //10 Check if any number is divisible by 5
        Boolean anyDivBy5 = numbers.stream().anyMatch(n -> n % 5 == 0);
        System.out.println("anyDivBy5: " + anyDivBy5);
        System.out.println("------------------");

        //11. Sort list in descending order
        List<Integer> numberInDesc = numbers.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println("numberInDesc: " + numberInDesc);
        System.out.println("------------------");

        //12. Remove duplicates from list
        List<Integer> numberWithOutDuplicate = numbers.stream().distinct().toList();
        System.out.println("numberWithOutDuplicate: " + numberWithOutDuplicate);
        System.out.println("------------------");

        //13. Find average of a list of numbers
        double avg = numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        System.out.println("avg: " + avg);
        System.out.println("------------------");

        //14. Group strings by length
        Map<Integer, List<String>> groupedByLength = names.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("groupedByLength: " + groupedByLength);
        System.out.println("------------------");

        //15. Join all strings with comma
        String joinedString = names.stream().reduce("", (a, b) -> {
            if (a == null || a.isEmpty()) {
                return b;
            } else {
                return a + ", " + b;
            }
        });
        String joinedString2 = names.stream().collect(Collectors.joining(", "));
        System.out.println("joinedString: " + joinedString);
        System.out.println("joinedString2: " + joinedString2);
        System.out.println("------------------");

        //16. Partition list into even and odd
        Map<Boolean, List<Integer>> evenOdPartition = numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("evenOdPartition: " + evenOdPartition);
        System.out.println("------------------");

        //17. Find second highest number
        double secondHighest = numbers.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElseThrow();
        System.out.println("secondHighest: " + secondHighest);
        System.out.println("------------------");

        //18: FlatMap list of lists
        List<List<String>> listOLists = List.of(List.of("a", "b"), List.of("c", "d"));
        List<String> flat = listOLists.stream()
                .flatMap(Collection::stream)
                .toList();
        System.out.println("flatList : " + flat); // [a, b, c, d]
        System.out.println("------------------");

        //19: Find frequency of each character in a string
        String input = "stream";
        Map<Character, Long> freq = input.chars().mapToObj(c -> (char) c)
                .collect(
                        Collectors.groupingBy(c -> c, Collectors.counting())
                );
        System.out.println("freq of chars : " + freq);
        System.out.println("------------------");

        //20: Get top 2 to 5 highest numbers (after first get max 3)
        List<Integer> afterFirst2Max3 = numbers.stream().sorted(Comparator.reverseOrder()).skip(2).limit(3).toList();
        List<Integer> afterFirst2Max30 = numbers.stream().sorted(Comparator.reverseOrder()).skip(2).limit(30).toList();
        System.out.println("afterFirst2Max3 : " + afterFirst2Max3);
        System.out.println("afterFirst2Max30 : " + afterFirst2Max30);
        System.out.println("------------------");


    }
}
