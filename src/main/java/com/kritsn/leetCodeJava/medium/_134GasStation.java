package com.kritsn.leetCodeJava.medium;

import java.util.Arrays;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station
 * to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
 * otherwise return -1. If there exists a solution, it is guaranteed to be unique.
 */
public class _134GasStation {

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
    int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;    // Total gas gain/loss across the circuit
        int tankBalance = 0; // Current gas tank balance
        int startPoint = 0;  // Starting index candidate

        for (int i = 0; i < gas.length; i++) {
            int gain = gas[i] - cost[i];
            totalGas += gain;
            tankBalance += gain;

            if (tankBalance < 0) {
                // Can't reach next station, so change start and reset tank
                startPoint = i + 1;
                tankBalance = 0;
            }
        }

        return totalGas >= 0 ? startPoint : -1;
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _134GasStation solver = new _134GasStation();

        // Test Case 1: Valid circuit exists
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Test Case 1: gas = " + Arrays.toString(gas1) + ", cost = " + Arrays.toString(cost1) + " -> Start Index = " + solver.canCompleteCircuit(gas1, cost1)); // Expected: 3

        // Test Case 2: No valid circuit
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Test Case 2: gas = " + Arrays.toString(gas2) + ", cost = " + Arrays.toString(cost2) + " -> Start Index = " + solver.canCompleteCircuit(gas2, cost2)); // Expected: -1

        // Test Case 3: Just enough at every step
        int[] gas3 = {5, 1, 2, 3, 4};
        int[] cost3 = {4, 4, 1, 5, 1};
        System.out.println("Test Case 3: gas = " + Arrays.toString(gas3) + ", cost = " + Arrays.toString(cost3) + " -> Start Index = " + solver.canCompleteCircuit(gas3, cost3)); // Expected: 4

        // Test Case 4: All equal
        int[] gas4 = {1, 1, 1, 1};
        int[] cost4 = {1, 1, 1, 1};
        System.out.println("Test Case 4: gas = " + Arrays.toString(gas4) + ", cost = " + Arrays.toString(cost4) + " -> Start Index = " + solver.canCompleteCircuit(gas4, cost4)); // Expected: 0

        // Test Case 5: Large single boost
        int[] gas5 = {10, 0, 0, 0};
        int[] cost5 = {1, 1, 1, 8};
        System.out.println("Test Case 5: gas = " + Arrays.toString(gas5) + ", cost = " + Arrays.toString(cost5) + " -> Start Index = " + solver.canCompleteCircuit(gas5, cost5)); // Expected: 0
    }
}
