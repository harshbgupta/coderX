package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 11, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 */
class _122MaxProfitInStockII {


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
    fun maxProfit(prices: IntArray): Int {
        var profit = 0 // Initialize total profit to 0

        // Iterate through the prices from the second day onwards
        for (i in 1 until prices.size) {
            // If today's price is higher than yesterday's, we can make a profit
            if (prices[i] > prices[i - 1]) {
                // Buy yesterday and sell today => add the profit
                profit += prices[i] - prices[i - 1]
            }
        }

        // Return the accumulated maximum profit
        return profit
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    // Test Case 1: Multiple ups and downs
    val prices1 = intArrayOf(7, 1, 5, 3, 6, 4)
    println("Test Case 1: prices = ${prices1.contentToString()} -> Max Profit = ${_122MaxProfitInStockII().maxProfit(prices1)}") // Expected: 7

    // Test Case 2: Strictly increasing prices
    val prices2 = intArrayOf(1, 2, 3, 4, 5)
    println("Test Case 2: prices = ${prices2.contentToString()} -> Max Profit = ${_122MaxProfitInStockII().maxProfit(prices2)}") // Expected: 4

    // Test Case 3: Strictly decreasing prices
    val prices3 = intArrayOf(7, 6, 4, 3, 1)
    println("Test Case 3: prices = ${prices3.contentToString()} -> Max Profit = ${_122MaxProfitInStockII().maxProfit(prices3)}") // Expected: 0

    // Test Case 4: Small zigzag pattern
    val prices4 = intArrayOf(2, 1, 2, 0, 1)
    println("Test Case 4: prices = ${prices4.contentToString()} -> Max Profit = ${_122MaxProfitInStockII().maxProfit(prices4)}") // Expected: 2

    // Test Case 5: Single element (no transactions possible)
    val prices5 = intArrayOf(10)
    println("Test Case 5: prices = ${prices5.contentToString()} -> Max Profit = ${_122MaxProfitInStockII().maxProfit(prices5)}") // Expected: 0
}
