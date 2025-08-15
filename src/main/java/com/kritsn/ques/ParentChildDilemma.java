package com.kritsn.ques;

class ParentChildDilemma extends Parent{
    String a = "Child";
    void test(){
        System.out.println("Child test"+a);
    }
}

class Child extends Parent{
    String a = "Child";
    void test(){
        System.out.println("Child test"+a);
    }
}

class Main{
    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.a); //this is var, so will Referent (parent) based on its type
        p.test(); //this is fun so this will reference based on object (child) type

    }
}