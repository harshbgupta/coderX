package com.kritsn.temp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Revisionjava {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Company company = new Company();
        Field name = Company.class.getDeclaredField("name");
        name.setAccessible(true);
        name.set(company, "kritsn");
        System.out.println("Company: "+ name.get(company));
        Method method = Company.class.getDeclaredMethods()[1];
        System.out.println(method.getName().equals("getDispayName"));
        method.setAccessible(true);
        method.invoke(company);

    }
}
class Company{
    private String name;

    private void getDispayName(){
        System.out.println("getDispayName called");
    }

    private void abc(){
        System.out.println("abc called");
    }
}