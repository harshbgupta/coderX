package com.kritsn.lld.designPattern;

import java.io.Serializable;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */
// =======> handling deserialization attack also as this implements Serializable
public class _01SingletonDemo implements Serializable {
    // final: prevents inheritance — if constructor visibility is ever loosened later, a subclass could bypass getInstance() and create a second instance(not exactly instance but a copy of it's filet and method in subcalss own object), breaking the Singleton guarantee
    // =======> handling deserialization attack also as this implements Serializable
    private static volatile _01SingletonDemo instance;

    private _01SingletonDemo() {
        // =======> handling reflection attack
        if (instance != null) {
            throw new IllegalStateException("Instance already exists — use getInstance()");
        }
    }

    public static _01SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (_01SingletonDemo.class) {
                if (instance == null) {
                    instance = new _01SingletonDemo();
                }
            }
        }
        return instance;
    }

    // =======> handling deserialization attack  -> only in case if calls implements Serializable
    // called automatically right after deserialization builds the new object
    protected Object readResolve() {
        return instance; // discard the newly deserialized object, return the real singleton
    }
}
