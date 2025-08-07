package com.kritsn.leetcodeKotlin

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 12, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station
 * to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
 * otherwise return -1. If there exists a solution, it is guaranteed to be unique.
 */
class _134GasStation {

    ///////////////////////////////////////////////////////////////////////////
    // Greedy Approach:
    // - Track total and current tank difference (gas[i] - cost[i]).
    // - If at any point tank < 0, reset tank and set new start to i + 1.
    // - If total sum of gas - cost < 0, no solution.
    //
    // 🪜 Steps:
    // 1. Initialize total = 0, tank = 0, start = 0.
    // 2. For each i in gas.indices:
    //    - gain = gas[i] - cost[i]
    //    - tank += gain, total += gain
    //    - If tank < 0 → reset tank = 0 and start = i + 1
    // 3. Return start if total >= 0 else -1.
    ///////////////////////////////////////////////////////////////////////////
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var totalGas = 0 // Total gas gain/loss across the circuit
        var tankBalance = 0  // Current gas tank balance
        var startPoint = 0 // Starting index candidate

        for (i in gas.indices) {
            val gain = gas[i] - cost[i]
            totalGas += gain
            tankBalance += gain

            if (tankBalance < 0) {
                // Can't reach next station, so change start and reset tank
                startPoint = i + 1
                tankBalance = 0
            }
        }

        return if (totalGas >= 0) startPoint else -1
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _134GasStation()

    // Test Case 1: Valid circuit exists
    val gas1 = intArrayOf(1, 2, 3, 4, 5)
    val cost1 = intArrayOf(3, 4, 5, 1, 2)
    println(
        "Test Case 1: gas = ${gas1.contentToString()}, cost = ${cost1.contentToString()} -> Start Index = ${
            solver.canCompleteCircuit(
                gas1,
                cost1
            )
        }"
    ) // Expected: 3

    // Test Case 2: No valid circuit
    val gas2 = intArrayOf(2, 3, 4)
    val cost2 = intArrayOf(3, 4, 3)
    println(
        "Test Case 2: gas = ${gas2.contentToString()}, cost = ${cost2.contentToString()} -> Start Index = ${
            solver.canCompleteCircuit(
                gas2,
                cost2
            )
        }"
    ) // Expected: -1

    // Test Case 3: Just enough at every step
    val gas3 = intArrayOf(5, 1, 2, 3, 4)
    val cost3 = intArrayOf(4, 4, 1, 5, 1)
    println(
        "Test Case 3: gas = ${gas3.contentToString()}, cost = ${cost3.contentToString()} -> Start Index = ${
            solver.canCompleteCircuit(
                gas3,
                cost3
            )
        }"
    ) // Expected: 4

    // Test Case 4: All equal
    val gas4 = intArrayOf(1, 1, 1, 1)
    val cost4 = intArrayOf(1, 1, 1, 1)
    println(
        "Test Case 4: gas = ${gas4.contentToString()}, cost = ${cost4.contentToString()} -> Start Index = ${
            solver.canCompleteCircuit(
                gas4,
                cost4
            )
        }"
    ) // Expected: 0

    // Test Case 5: Large single boost
    val gas5 = intArrayOf(10, 0, 0, 0)
    val cost5 = intArrayOf(1, 1, 1, 8)
    println(
        "Test Case 5: gas = ${gas5.contentToString()}, cost = ${cost5.contentToString()} -> Start Index = ${
            solver.canCompleteCircuit(
                gas5,
                cost5
            )
        }"
    ) // Expected: 0
}
