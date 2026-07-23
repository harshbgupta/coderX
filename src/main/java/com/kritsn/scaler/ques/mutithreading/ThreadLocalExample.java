package com.kritsn.scaler.ques.mutithreading;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 16, 2025
 */

public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 100);
    private static ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal1.set(1);
        new Thread(() -> {
            int value = threadLocal.get();
            value += 1;
            threadLocal.set(value);
            threadLocal1.set(value);
            System.out.println(Thread.currentThread().getName() + " → " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + " → " + threadLocal1.get());
        }).start();

        new Thread(() -> {
            int value = threadLocal.get();
            value += 1;
            threadLocal.set(value);
            threadLocal1.set(value);
            System.out.println(Thread.currentThread().getName() + " → " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + " → " + threadLocal1.get());
        }).start();
    }
}
