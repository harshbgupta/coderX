package com.kritsn.ivs.epam;

import java.util.*;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 23, 2025
 */

///////////////////////////////////////////////////////////////////////////
// Given a string, find the words with the maximum number of vowels. Must use java 8 streams API.
//
// inputs
//
// String: "The quick brown fox jumps right over the little lazy dog." and maxNoOfvowels =2
//
// output: [quick, over, little]
///////////////////////////////////////////////////////////////////////////

public class Main {

    public static void main(String[] args) {
        String sentence = "The quick brown fox jumps right over the little lazy dog.";
        int maxNoOfVowels = 2;
        List<String> result = findWordsWithMaxVowels(sentence, maxNoOfVowels);
        System.out.println(result);

        ///////////////////////////////////////////////////////////////////////////
        // 2nd Ques
        ///////////////////////////////////////////////////////////////////////////
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(3, "Alice"));
        employees.add(new Employee(4, "Alice"));
        employees.add(new Employee(1, "Bob"));
        employees.add(new Employee(2, "Charlie"));
        Collections.sort(employees, Comparator.comparing(Employee::getName).thenComparing((Employee::getId)));
        System.out.println(employees);

        ///////////////////////////////////////////////////////////////////////////
        // 3rd Ques
        ///////////////////////////////////////////////////////////////////////////
        Parent p = new Child();
        System.out.println(p.name);// parent
        p.print();

    }

    public static List<String> findWordsWithMaxVowels(String sentence, int maxNoOfVowels) {
        List<String> wordsWithTwoVowels = Arrays.stream(sentence.split(" ")).toList().stream()
                .filter(word -> hasTwoVowels(word)).toList();

//        System.out.println(wordsWithTwoVowels);
        return wordsWithTwoVowels;
    }

    private static boolean hasTwoVowels(String word) {

        return word.matches(".*[aeiou].*.*[aeiou].*");
    }

}


/// ////////////////////////////////////////////////////////////////////////
//

/// ////////////////////////////////////////////////////////////////////////
class Employee {

    private int id;

    private String name;

    public Employee(int id, String name) {

        this.id = id;

        this.name = name;

    }

    public String toString() {

        return "Employee{id=" + id + ", name='" + name + "'}";

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

/// ////////////////////////////////////////////////////////////////////////
//

///////////////////////////////////////////////////////////////////////////
class Parent {

    String name = "Parent";

    void print() {

        System.out.println("Parent print()");

    }

}

class Child extends Parent {

    String name = "Child";

    @Override
    void print() {

        System.out.println("Child print()");

    }

}


