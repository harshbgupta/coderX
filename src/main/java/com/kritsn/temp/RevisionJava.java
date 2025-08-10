package com.kritsn.temp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Revisionjava {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Company company = new Company();
        company.setName("Kritsn");
        String  name = (String) company.getName();
        System.out.println(name);
        company.setName(1);
        int  nameInt = (int) company.getName();
        System.out.println(nameInt);

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