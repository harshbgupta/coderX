package com.kritsn.leetCodeJava;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 * <p>
 * Find and return the maximum profit you can achieve.
 */
public class _122MaxProfitInStockII {

    ///////////////////////////////////////////////////////////////////////////
    // Whenever the price on the next day is higher than the current day, we should buy today and
    // sell tomorrow to make a profit. The **sum of all such positive differences** gives the maximum profit.
    //
    // ### 🪜 Steps:
    //
    // 1. Initialize a `profit` variable to `0`.
    // 2. Iterate over `prices` from day 1 to day n-1.
    // 3. If `price[i] > price[i-1]`, it means profit can be made:
    //     - Add `price[i] - price[i-1]` to total `profit`.
    // 4. Return total `profit`.
    ///////////////////////////////////////////////////////////////////////////
    int maxProfit(int[] prices) {
        int profit = 0; // Initialize total profit to 0

        // Iterate through the prices from the second day onwards
        for (int i = 1; i < prices.length; i++) {
            // If today's price is higher than yesterday's, we can make a profit
            if (prices[i] > prices[i - 1]) {
                // Buy yesterday and sell today => add the profit
                profit += prices[i] - prices[i - 1];
            }
        }

        // Return the accumulated maximum profit
        return profit;
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        // Test Case 1: Multiple ups and downs
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test Case 1: prices = " + Arrays.toString(prices1) + " -> Max Profit = " + new _122MaxProfitInStockII().maxProfit(prices1)); // Expected: 7

        // Test Case 2: Strictly increasing prices
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 2: prices = " + Arrays.toString(prices2) + " -> Max Profit = " + new _122MaxProfitInStockII().maxProfit(prices2)); // Expected: 4

        // Test Case 3: Strictly decreasing prices
        int[] prices3 = {7, 6, 4, 3, 1};
        System.out.println("Test Case 3: prices = " + Arrays.toString(prices3) + " -> Max Profit = " + new _122MaxProfitInStockII().maxProfit(prices3)); // Expected: 0

        // Test Case 4: Small zigzag pattern
        int[] prices4 = {2, 1, 2, 0, 1};
        System.out.println("Test Case 4: prices = " + Arrays.toString(prices4) + " -> Max Profit = " + new _122MaxProfitInStockII().maxProfit(prices4)); // Expected: 2

        // Test Case 5: Single element (no transactions possible)
        int[] prices5 = {10};
        System.out.println("Test Case 5: prices = " + Arrays.toString(prices5) + " -> Max Profit = " + new _122MaxProfitInStockII().maxProfit(prices5)); // Expected: 0
    }
}
