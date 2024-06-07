package com.vertical.leetcode.arrays

import toJsonString

fun main(args: Array<String>) {
    println("Result: " + toJsonString(maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))))
}

/**
 * Best Time to Buy and Sell Stock
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
private fun maxProfit(prices: IntArray): Int {
    var maxProfit = 0
    var investPrice = prices[0]
//    for ((index:, price)  in prices.withIndex()) {
    for (i in 1 until prices.size) {
        val price = prices[i]
        if (investPrice > price) {
            investPrice = price
        } else if ((price - investPrice) > maxProfit) {
            maxProfit = price - investPrice
        }
//        logger("index: $i", "price: $price", "investPrice: $investPrice", "maxProfit: $maxProfit")
    }
    return maxProfit
}