package com.kritsn.quesJava;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/*
 * 📘 Advanced Java Stream API Questions and Answers
 * Author: Harsh Gupta
 * Use this doc to master real-world and interview-focused Stream API use cases.
 */
public class StreamJava {


    // 1. Remove duplicates from a list of objects
    static void removeDuplicates() {
        List<String> list = Arrays.asList("a", "b", "c", "a", "b");
        List<String> unique = list.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique Elements: " + unique);
    }

    // 2. Group employees by department
    static void groupByDepartment() {
        record Employee(String name, String dept) {
        }
        List<Employee> employees = List.of(
                new Employee("Alice", "HR"),
                new Employee("Bob", "IT"),
                new Employee("Charlie", "IT"),
                new Employee("David", "HR")
        );

        Map<String, List<Employee>> deptMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept));

        System.out.println("Grouped by Department: " + deptMap);
    }

    // 3. Compute second highest salary
    static void secondHighestSalary() {
        List<Integer> salaries = List.of(3000, 5000, 7000, 5000, 9000);

        Optional<Integer> secondHighest = salaries.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();

        System.out.println("Second Highest Salary: " + secondHighest.orElse(-1));
    }

    // 4. Remove empty strings from list
    static void removeEmptyStrings() {
        List<String> list = List.of("apple", "", "banana", "", "cherry");
        List<String> filtered = list.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        System.out.println("Filtered List: " + filtered);
    }

    // 5. Group transactions by currency and sum amount
    static void sumByCurrency() {
        record Transaction(String currency, int amount) {
        }

        List<Transaction> txns = List.of(
                new Transaction("USD", 100),
                new Transaction("INR", 200),
                new Transaction("USD", 300),
                new Transaction("INR", 100)
        );

        Map<String, Integer> grouped = txns.stream()
                .collect(Collectors.groupingBy(
                        Transaction::currency,
                        Collectors.summingInt(Transaction::amount)));

        System.out.println("Sum by Currency: " + grouped);
    }

    // 6. toMap vs groupingBy explanation
    static void toMapVsGroupingBy() {
        List<String> names = List.of("John", "Alice", "Bob", "Alice");

        // toMap throws exception for duplicate keys
        try {
            Map<String, Integer> map = names.stream()
                    .collect(Collectors.toMap(Function.identity(), String::length));
            System.out.println("toMap: " + map);
        } catch (Exception e) {
            System.out.println("toMap error: Duplicate keys!");
        }

        // groupingBy groups them
        Map<String, List<String>> group = names.stream()
                .collect(Collectors.groupingBy(Function.identity()));
        System.out.println("groupingBy: " + group);
    }

    // 7. Flatten Map<String, List<String>> into List<String>
    static void flattenMapValues() {
        Map<String, List<String>> map = Map.of(
                "A", List.of("a1", "a2"),
                "B", List.of("b1", "b2")
        );

        List<String> flat = map.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println("Flattened List: " + flat);
    }

    // 8. Most frequent element
    static void mostFrequentElement() {
        List<String> list = List.of("a", "b", "a", "c", "a", "b");
        String result = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("Most Frequent: " + result);
    }

    // 9. Double only even numbers
    static void doubleEvenNumbers() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> doubled = list.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println("Doubled Evens: " + doubled);
    }

    // 10. Pagination: skip N, limit M
    static void paginateList() {
        List<String> data = List.of("one", "two", "three", "four", "five", "six", "seven");
        int page = 2, size = 3; // Page 2 with 3 items per page
        List<String> paginated = data.stream()
                .skip((page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
        System.out.println("Page " + page + ": " + paginated);
    }

    public static void main(String[] args) {
        removeDuplicates();
        groupByDepartment();
        secondHighestSalary();
        removeEmptyStrings();
        sumByCurrency();
        toMapVsGroupingBy();
        flattenMapValues();
        mostFrequentElement();
        doubleEvenNumbers();
        paginateList();
    }
}
