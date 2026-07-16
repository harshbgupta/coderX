package com.kritsn.ivs.siemens;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 08, 2025
 */

public class Test {

    public static void main(String[] args) {


    }

    private static void sol(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) return;

        //normal
        List<Employee> seniors= employees.stream().filter(employee -> employee.getAge() > 30).toList();
        List<Employee> jinors= employees.stream().filter(employee -> employee.getAge() <= 30).toList();

        List<Employee> newseniors = seniors.stream().map(employee -> {
            employee.setTYPE("Senior");
            return employee;
        }).toList();

        List<Employee> newData = jinors.stream().map(employee -> {
            employee.setTYPE("Junior");
            return employee;
        }).toList();





        //optmised
        employees.stream().map(employee ->{
            if (employee.getAge() > 30) {
                employee.setTYPE("Senior");
            } else {
                employee.setTYPE("Junior");
            }
            return employee;
        }).toList();
    }

    private static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
//        employees.add(Employee())
        return null;
    }


    class  Employee{
        private int id;
        private String name;
        private int age;
        private String TYPE;

        public Employee(int id, String name, int age, String TYPE) {
            super();
            this.id = id;
            this.name = name;
            this.age = age;
            this.TYPE = TYPE;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getTYPE() {
            return TYPE;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }
    }
}
