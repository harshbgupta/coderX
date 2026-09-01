package com.kritsn.leetcodeKotlin
import kotlin.math.max

fun main(args: Array<String>) {
    val prices = intArrayOf(7, 1, 5, 3, 6, 4)
    val profit = maxProfit(prices)
    println("Prices ${prices.contentToString()} and max Profit: $profit ")
}

/**
 * Best Time to Buy and Sell Stock
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
private fun maxProfit(prices: IntArray): Int {
    // If we have fewer than two days, we can't make a profit.
    if (prices.size < 2) {
        return 0
    }

    var minPrice = prices[0]
    var maxProfit = 0

    // Iterate through the prices starting from the second day.
    for (i in 1 until prices.size) {
        val currentPrice = prices[i]

        // Check for a new maximum profit by selling at the current price.
        val potentialProfit = currentPrice - minPrice
        maxProfit = max(maxProfit, potentialProfit)

        // Update the minimum price seen so far for future transactions.
        if (currentPrice < minPrice) {
            minPrice = currentPrice
        }
    }

    return maxProfit
}