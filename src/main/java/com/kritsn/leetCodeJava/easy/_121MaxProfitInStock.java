package com.kritsn.leetCodeJava.easy;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

public class _121MaxProfitInStock {

    /**
     * Best Time to Buy and Sell Stock
     * <p>
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     */
    private int maxProfit(int[] prices) {
        // If we have fewer than two days, we can't make a profit.
        if (prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;

        // Iterate through the prices starting from the second day.
        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];

            // Check for a new maximum profit by selling at the current price.
            int potentialProfit = currentPrice - minPrice;
            maxProfit = Math.max(maxProfit, potentialProfit);

            // Update the minimum price seen so far for future transactions.
            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int profit = new _121MaxProfitInStock().maxProfit(prices);
        System.out.println("Prices " + Arrays.toString(prices) + " and max Profit: " + profit + " ");
    }
}
