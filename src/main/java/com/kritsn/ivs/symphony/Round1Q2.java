package com.kritsn.ivs.symphony;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 18, 2026
 */

/*
String pattern = "abba";
    String str = "dog cat cat dog";

Question: funtion accetps 2 strings pattern and snetence, need to return boolean if sentence foloow the same parrtend like forst and last charanter repeating in pattern so first nad last word is reptaing in sentense, (true) else false

 */
public class Round1Q2 {


    public static void main(String[] args) {
    }
}


final class Employee {
    private String id;
    private String managerId;
    private String name;
    private String department;
    private Double salary;

    public Employee(String id, String managerId, String name, String department, Double salary) {
        this.id = id;
        this.managerId = managerId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getManagerId() {
        return managerId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public Double getSalary() {
        return salary;
    }
}

//design chance
//get the total salary for empId (if this is a manager then total for all emp incldung him)
//check Employee works under a manger, pass only empId and ManagerId

class Cache {
    EmployeeTree root = null;
    Map<String, Employee> employeeCache = new HashMap<>();
    Map<String, Double> salaryCache = new HashMap<>();

    Map<String, List<Employee>> subEmpCache = new HashMap<>();
    Map<String, List<String>> subEmpIdCache = new HashMap<>();


    public boolean chekcEmpUderManager(String managerId, String empId) {
        List<String> employees = subEmpIdCache.get(managerId);
        if (employees == null || employees.isEmpty()) return false;

        return employees.contains(empId);


//        List<Employee> employees =  subCache.get(managerId);
//        if(employees== null || employees.isEmpty()) return false;
//
//        for (Employee emp :employees ){
//            if(emp.getId() == empId) return true;
//        }
//
//        return false
    }

    public double getTotalSalary(String empId) {
        double salary = 0.0;
        if (subEmpCache.containsKey(empId)) {
            // this is manager
            List<Employee> employees = subEmpCache.get(empId);
            salary = salaryCache.get(empId);
            for (Employee emp : employees) {
                salary += salaryCache.get(emp.getId());
            }
        } else {
            //thos is emp
            salary = salaryCache.get(empId);
        }
        return salary;
    }


    public double getTotalSalaryTree(String empId) {
        EmployeeTree curr = root;
        if (curr.id == empId) {
            return recursive(root);
        } else {
            while (curr.subOrdinates != null && curr.subOrdinates.isEmpty()) {
                for (EmployeeTree emp : curr.subOrdinates) {
                    if (emp.id == empId) {
                        return recursive(emp);
                    }
                }
            }
        }
        return 0.0;
    }

    public double recursive(EmployeeTree emp) {
        if (emp.subOrdinates == null || emp.subOrdinates.isEmpty()) return emp.salary;

        Double salary = 0.0;
        for (EmployeeTree tre : emp.subOrdinates) {
            salary += recursive(tre);
        }

        return salary;
    }

}


class EmployeeTree {
    String id;
    Double salary;
    List<EmployeeTree> subOrdinates;
}