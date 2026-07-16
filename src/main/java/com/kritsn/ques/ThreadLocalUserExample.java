package com.kritsn.ques;

class UserContext {
    private static final ThreadLocal<String> currentUser = new ThreadLocal<>();

    public static void set(String user) { currentUser.set(user); }
    public static String get() { return currentUser.get(); }
    public static void clear() { currentUser.remove(); } // avoid memory leak
}

public class ThreadLocalUserExample {
    public static void main(String[] args) {
        Runnable task = () -> {
            UserContext.set(Thread.currentThread().getName());
            System.out.println("User in " + Thread.currentThread().getName() + ": " + UserContext.get());
            UserContext.clear(); // important!
        };

        new Thread(task, "Harsh").start();
        new Thread(task, "Gupta").start();
    }
}