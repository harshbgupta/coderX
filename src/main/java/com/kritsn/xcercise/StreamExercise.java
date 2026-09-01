package com.kritsn.xcercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 10, 2026
 */

public class StreamExercise {
    public static void main(String[] args) {
        StreamExercise ex = new StreamExercise();
        ex.easyQuestions();
        System.out.println("--------------");
        ex.mediumQuestions();
        System.out.println("--------------");
        ex.hardQuestions();
        System.out.println("--------------");
        ex.extraQuestions();
    }

    /* Easy:
        1. Q: How do you convert a List<String> into uppercase using Stream API?
        2. Q: How do you sort a List<Integer> in ascending and descending order?
    */
    List<String> names = Arrays.asList("radha", "krishna", "shiva", "sati", "shankar", "shankar");

    public void easyQuestions() {
        //ques1:
        names.stream().map(String::toUpperCase).forEach(System.out::print);

        //ques2:
        System.out.println("---");
        names.stream().sorted(Comparator.reverseOrder()).forEach(System.out::print);
    }


    /* Medium
        1.  Q: Sort a list of Employees by salary (ascending) and then by name (alphabetical).
        2.  Q: Find the second highest salary from an Employee list.
        3.  Q: Difference between parallelStream() and normal stream()?_> stream() → sequential processing (one element at a time). & -   parallelStream() → divides work across multiple threads (ForkJoinPool).
        4.  Q: How to remove duplicates from a list using Streams?

    */
    List<Employee> employees = Arrays.asList(
            new Employee("radha", 20),
            new Employee("krishna", 20),
            new Employee("shiva", 34),
            new Employee("sati", 30),
            new Employee("shankar", 34)
    );

    record Employee(String name, int salary) {
    }

    public void mediumQuestions() {
        //ques1:
        employees.stream().sorted(Comparator.comparing(Employee::salary).thenComparing(Comparator.comparing(Employee::name).reversed())).
                forEach(System.out::println);
        //ques2:

        System.out.println("---");
        Employee secondHighestSalary = employees.stream().sorted(Comparator.comparingInt(Employee::salary).reversed()).skip(1).findFirst().orElse(null);
        System.out.println(secondHighestSalary);

        //ques4
        System.out.println("---");
        employees.stream().distinct().forEach(System.out::println);
    }

    /* HARD:
            1.  Q: Given a list of transactions, group them by currency and sort each group by amount.
            2.  Q: Find the top 2 highest paid employees in each currency.
            3.  Q: Difference between map() and flatMap() in Streams?
    */
    List<Transaction> transactions = Arrays.asList(
            new Transaction("$", 2000),
            new Transaction("INR", 500000),
            new Transaction("$", 3400),
            new Transaction("INR", 34500),
            new Transaction("$", 3300)
    );

    //-----
    record Transaction(String currency, long amount) {
    }

    public void hardQuestions() {
        //ques1:
        Map<String, List<Transaction>> result1= transactions.stream().collect(Collectors.groupingBy(
                Transaction::currency,
                Collectors.collectingAndThen(Collectors.toList(), list-> list.stream().sorted(Comparator.comparingLong(Transaction::amount)).toList())));
        result1.entrySet().stream().forEach(System.out::println);
        //ques2:
        System.out.println("---");
        Map<String, List<Transaction>> result2= transactions.stream().collect(Collectors.groupingBy(
                Transaction::currency,
                Collectors.collectingAndThen(Collectors.toList(),  transactions -> transactions.stream().sorted(Comparator.comparingLong(Transaction::amount).reversed()).limit(2).toList())));
        result2.entrySet().stream().forEach(System.out::println);
    }

    /* Interview Question
        1.  Q: Find out the max occurrences character the List
    */
    List<String> list = Arrays.asList("A", "B", "A", "B", "A", "B", "A", "C", "A", "C", "A", "C");

    public void extraQuestions() {
        //ques1:
        Map.Entry<String, Long> mostOccurred = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();

        System.out.println(mostOccurred.getKey() + " -> " + mostOccurred.getValue()); // A -> 6
    }
}
