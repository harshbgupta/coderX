package com.kritsn.scaler.lld;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since June 25, 2025
 */
public class _02CallableExample {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(10);
        for (int i = 0; i <= 100; i++) {
            ex.submit(new NumberPrinterCallable(i));
        }
    }
}

class NumberPrinterCallable implements Callable<Integer> {

    private int numberToPrint;
    public NumberPrinterCallable(int numberToPrint) {
        this.numberToPrint = numberToPrint;
    }
    @Override
    public Integer call() throws Exception {
        System.out.println("Number to print " + Thread.currentThread().getName() + ", number: " + numberToPrint);
        return numberToPrint;
    }
}