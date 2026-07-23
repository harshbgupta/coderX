package com.kritsn.scaler.ques;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 16, 2025
 */

public class Temp2 {
    public static void main(String[] args) {
        AInterface ai = new Demo();
        ai.m1();
        System.out.println("--------------");
        /*BInterface bi = new Demo();
        bi.m1();
        System.out.println("--------------");
        CInterface ci = new Demo();
        ci.m1();*/
        System.out.println("--------------");
        Demo d = new Demo();
        d.m1();
    }
}

class Demo implements AInterface{


}

interface AInterface {
    default void m1() {
        System.out.println("AInterface m1");
    }

}

interface BInterface {
    default void m1() {
        System.out.println("BInterface m1");
    }

    static void mew(){
        System.out.println("BInterface Static");
    }

}

interface CInterface {
    default void m1() {
        System.out.println("CInterface m1");
    }

}