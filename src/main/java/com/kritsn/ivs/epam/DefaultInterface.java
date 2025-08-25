package com.kritsn.ivs.epam;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 23, 2025
 */

public class DefaultInterface {
    public static void main(String[] args) {
        I obj = new Impl();
        obj.show();
    }
}

interface I {
    default void show() {
        System.out.println("Interface default method");
    }

    default void temp() {
        System.out.println("Interface default method");
    }

}

class Impl implements I {

    public void show() {
        System.out.println("Impl method");
    }

}
