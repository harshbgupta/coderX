package com.kritsn.ques.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 25, 2025
 */

public class Stream1 {
    public static void main(String[] args) {
        name();
        personList();
        transactionList();
    }

    private static void name() {
        List<String> names = Arrays.asList("radha", "krishna", "shiva", "sati", "shankar", "shankar");

        //Q: Copy the list by using the stream
        List<String> nameCopy = names.stream().toList(); //copy the list using Stream
        System.out.println("name Copied: " + nameCopy); //here nameCopy is Immutable you can update/add/remove anything

        //Q: Sort the list by using the stream
        List<String> nameSorted = names.stream().sorted().toList();// sort the using comparable list
        System.out.println("name sort order: " + nameSorted);

        //Q: Reverse the list by using the stream
        Collections.reverse(names); //just normal reverse
        System.out.println("name reverse order using Collections: " + nameCopy);

        //Q: Sort & Reverse the list by using the stream
        List<String> nameSortedReversed = names.stream().sorted(Comparator.reverseOrder()).toList(); // sort the list using comparator
        System.out.println("name sorted but reverse: " + nameSortedReversed);

        //How to remove duplicates from a list using Streams?
        List<String> distinctNames = names.stream().distinct().toList();
        System.out.println("distinct name : " + distinctNames);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<String, List<Integer>> evenOddMap = numbers.stream()
                .collect(Collectors.groupingBy(
                        n -> n % 2 == 0 ? "Even" : "Odd"  // classifier function
                ));

        System.out.println("Even: " + evenOddMap.get("Even"));
        System.out.println("Odd: " + evenOddMap.get("Odd"));
    }

    private static void personList() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("radha", 20),
                new Employee("krishna", 18),
                new Employee("shiva", 34),
                new Employee("sati", 30),
                new Employee("shankar", 33)
        );

        //Q: Sort a list of Employees by age (ascending)
        List<Employee> sortByAge = employeeList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).toList(); // sort in reverse order the list using comparator
        System.out.println("person list sort by age ASC: " + sortByAge);

        //Q: Sort a list of Employees by age (descending)
        List<Employee> sortByAgeReverse = employeeList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).toList(); // sort in reverse order the list using comparator
        System.out.println("person list sort by age DSC: " + sortByAgeReverse);

        //Q: Sort a list of Employees by salary (ascending) and then by name (alphabetical).
        List<Employee> sortByAgeThenName = employeeList.stream().sorted(Comparator.comparing(Employee::getSalary).thenComparing(Comparator.comparing(Employee::getName))).toList(); // sort in reverse order the list using comparator
        System.out.println("person list sort by salary: " + sortByAgeThenName);

        //Q: Find the second-highest age (Only) from an Employee list.
        int salarySecondSalary = employeeList.stream()
                .map(Employee::getSalary)
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow();
        System.out.println("2nd Highest salary: " + salarySecondSalary);

        //Q: Find the second-highest salary employee from an Employee list.
        Employee salarySecondHighestSalariedEmployee = employeeList.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).skip(1).findFirst().orElse(null);
        System.out.println("2nd Highest salary person: " + salarySecondHighestSalariedEmployee);

        ///////////////////////////////////////////////////////////////////////////
        //  Q: Difference between parallelStream() and normal stream()?
        //  A:
        //    •   stream() → sequential processing (one element at a time).
        //    •   parallelStream() → divides work across multiple threads (ForkJoinPool).
        //    •   Use only for large data + stateless operations.
        ///////////////////////////////////////////////////////////////////////////
    }

    private static void transactionList() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("$", 2000),
                new Transaction("INR", 500000),
                new Transaction("$", 3400),
                new Transaction("INR", 34500),
                new Transaction("$", 3300)
        );

        //<< M Imp >> Q: Given a list of transactions, group them by currency and sort each group by amount.
        Map<String, List<Transaction>> transactionGroupedBy =
                transactions.stream().collect(
                        Collectors.groupingBy(
                                Transaction::getCurrency,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        txns -> txns.stream()
                                                .sorted(Comparator.comparing(Transaction::getAmount))
                                                .toList()
                                )
                        )
                );

        System.out.println("transaction all grouped by currency" + transactionGroupedBy);

        //Q: Find the top 3 highest amount transactions in each currency.
        Map<String, List<Transaction>> transactionGroupedByTop3 =
                transactions.stream().collect(
                        Collectors.groupingBy(
                                Transaction::getCurrency,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        txns -> txns.stream()
                                                .sorted(Comparator.comparing(Transaction::getAmount).reversed()).limit(3)
                                                .toList()
                                )
                        )
                );
        System.out.println("transaction top 3 amount for each currency" + transactionGroupedByTop3);


        //Q: Write a Stream pipeline to find the most frequent word in a list of strings.
        Transaction mostFreq = transactions.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();

        System.out.println("most frequent word transaction: " + mostFreq);

        ///////////////////////////////////////////////////////////////////////////
        // ⚡ Sorting Based on Different Parameters (Quick Patterns)
        //
        //Requirement Comparator Example
        //Ascending by name: Comparator.comparing(Employee::getName)
        //Descending by salary: Comparator.comparing(Employee::getSalary).reversed()
        //Multiple fields: Comparator.comparing(Employee::getDept).thenComparing(Employee::getName)
        //Null values first: Comparator.nullsFirst(Comparator.naturalOrder())
        //Case-insensitive String: Comparator.comparing(String::toLowerCase)
        ///////////////////////////////////////////////////////////////////////////

    }


    ///////////////////////////////////////////////////////////////////////////
    // inner class

    /// ////////////////////////////////////////////////////////////////////////
    static class Employee {
        private String name;
        private int salary;

        public Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public int getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "(" + name + "," + salary + ")";
        }
    }

    static class Transaction {
        private String currency;
        private int amount;

        public Transaction() {
        }

        public Transaction(String currency, int amount) {
            this.currency = currency;
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "(" + currency + " " + amount + ")";
        }
    }
}
