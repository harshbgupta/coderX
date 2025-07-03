package com.kritsn.kh;

public class ApiCall {

    public static void main(String[] args) {
        millisecondsToEpoch();
    }

    public static void millisecondsToEpoch() {
        long end = System.currentTimeMillis();
        long start = end - 20 * 60 * 1000;
        double startDate = start / 1000.0;
        double endDate = end / 1000.0;
        String formattedStartDate= String.format("%.3f", startDate);
        String formattedEndDate= String.format("%.3f", endDate);
        System.out.println("date startDate " + formattedStartDate + ", end Date " + formattedEndDate);
    }
}

