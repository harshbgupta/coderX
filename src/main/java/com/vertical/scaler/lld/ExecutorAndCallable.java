package com.vertical.scaler.lld;

//Question 1: print 1 to 100 on different threads
public class ExecutorAndCallable {

    public static void main(String[] args) {
        for (int i=1; i<=100;i++){
//            NumberPrinter numberPrinter = new NumberPrinter(i);
//            Thread t = new Thread(numberPrinter);
//            t.start();

            NumberPrinterThread numberPrinterThread = new NumberPrinterThread(i);
            numberPrinterThread.start();
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
