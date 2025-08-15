package com.kritsn.temp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

class Revisionjava {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, IOException {
//        Company company = new Company();
//        company.setName("Kritsn");
//        String  name = (String) company.getName();
//        System.out.println(name);
//        company.setName(1);
//        int  nameInt = (int) company.getName();
//        System.out.println(nameInt);


        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter your name: ");
//            String name = sc.nextLine();
//            System.out.println("Hello, " + name + "!");
        } finally {
            System.out.println("finally");
        }

    }
}
class Company{
    private Object name;

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }
}