package com.kritsn.scaler.lld;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Question 1: print 1 to 100 on different threads
public class _01ExecutorExample {

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(10);
        for (int i=1; i<=100;i++){
            //using implementation Runnable
            NumberPrinter numberPrinter = new NumberPrinter(i);
            Thread t = new Thread(numberPrinter);
            t.start();

            //using extending Thread
            NumberPrinterThread numberPrinterThread = new NumberPrinterThread(i);
            numberPrinterThread.start();

            //using executers
            ex.execute(new NumberPrinterThread(i));
        }

    }

}

class NumberPrinter implements Runnable {

    private int numberToPrint;

    public NumberPrinter(int numberToPrint) {
        this.numberToPrint = numberToPrint;
    }

    @Override
    public void run() {
        System.out.println("Number to print " + Thread.currentThread().getName() + ", number: " + numberToPrint);
    }
}

class NumberPrinterThread extends Thread {

    private int numberToPrint;

    public NumberPrinterThread(int numberToPrint) {
        this.numberToPrint = numberToPrint;
    }

    @Override
    public void run() {
        System.out.println("Number to print " + Thread.currentThread().getName() + ", number: " + numberToPrint);
    }
}
