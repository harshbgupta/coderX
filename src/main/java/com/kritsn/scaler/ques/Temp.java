package com.kritsn.scaler.ques;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 16, 2025
 */

public class Temp {
    public static void main(String[] args) {
        A a = new A();
        A ab = new B();
        A ac = new C();
        B b = new B();
        C c = new C();
//        System.out.println("-----------------------");
//        System.out.println(a.temp);
//        System.out.println(ab.temp);
//        System.out.println(ac.temp);
//        System.out.println(a.temp);
//        System.out.println(b.temp);
//        System.out.println(c.temp);

        System.out.println("-----------------------");
        a.m1();
        ab.m1();
        ac.m1();

//        System.out.println("-----------------------");
//        a.m1a();
//        ((B) ab).m1b();
//        ((C) ac).m1c();
    }

}


class A {
    String temp = "class A";

    void m1() {
        System.out.println("A m1");
    }

    public void m1a() {
        System.out.println("A m1a");
    }
}


class B extends A {
    String temp = "class B";

    @Override
    void m1() {
        System.out.println("B m1");
    }

    public void m1b() {
        System.out.println("B m1b");
    }
}

class C extends A {
    String temp = "class C";

    @Override
    public void m1() {
        System.out.println("C m1");
    }

    public void m1c() {
        System.out.println("C m1c");
    }
}