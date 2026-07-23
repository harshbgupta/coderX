package com.kritsn.scaler.ques.stream;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 21, 2025
 */

public class JavaStream {

//    public static void main(String[] args) {
        /*List<Integer> numers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<String, List<Integer>> map = numers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0))
                .entrySet().stream()
                .collect(Collectors.toMap(entry -> {
                            if (entry.getKey()) return "Even";
                            else return "Odd";
                        },
                        entry -> {
                            return entry.getValue();
                        }
                ));
        System.out.print("ans: "+" "+map);*/
        static void modifyPrimitive ( int x){
            x = 50; // change local copy
        }

        static void modifyObject (Person p){
            p.name = "Changed"; // modifies object via reference copy
        }

        static void reassignObject (Person p){
            p = new Person("New Person"); // reassigns local copy only
        }

        public static void main (String[]args){
            // Case 1: Primitive type
            int a = 10;
            modifyPrimitive(a);
            System.out.println("After modifyPrimitive, a = " + a);
            // ✅ Output: 10 (unchanged, because only a copy was modified)

            // Case 2: Object field modification
            Person person = new Person("Original");
            modifyObject(person);
            System.out.println("After modifyObject, person.name = " + person.name);
            // ✅ Output: Changed (field modified, reference copy still points to same object)

            // Case 3: Object reference reassignment
            reassignObject(person);
            System.out.println("After reassignObject, person.name = " + person.name);
            // ✅ Output: Changed (original object not replaced, because reassignment was local)
        }
//    }

}

class Person {
    String name;

    Person(String name) {
        this.name = name;
    }
}
