package com.kritsn.ivs.agoda;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 03, 2026
 */

public class Round1 {

    public static void main(String[] args) {

    }

    /*
    You have given a chemical formula (only Contains C, H, O, digits) U need to tell me its weight & number are denote
    as preceding element are that times -> C: 12, H: 1, O: 8
     */
    /**
     * APPROACH: Linear scan + parse digits
     *
     * STEPS:
     * 1. Iterate through formula
     * 2. When you see element (C, H, O):
     *    ├─ Get element weight
     *    ├─ Extract count after element
     *    ├─ Add (weight × count) to total
     * 3. Return total
     *
     * TIME: O(N) - single pass
     * SPACE: O(1) - constant space
     */
    public static int getAtomicWeightNoBrackets(String formula) {

        // Step 1: Create weight mapping
        // Why: Quick lookup for element weight
        Map<Character, Integer> weights = new HashMap<>();
        weights.put('C', 12);
        weights.put('H', 1);
        weights.put('O', 8);

        // Step 2: Initialize total weight
        // Why: Accumulate weight as we parse
        int totalWeight = 0;

        // Step 3: Parse formula character by character
        // Why: Build weight incrementally
        for (int i = 0; i < formula.length(); ) {

            // Step 4: Check if current character is element
            // Why: Identify where element starts
            if (formula.charAt(i) == 'C' ||
                    formula.charAt(i) == 'H' ||
                    formula.charAt(i) == 'O') {

                // Step 5: Get element and its weight
                // Why: Know how much one atom weighs
                char element = formula.charAt(i);
                int elementWeight = weights.get(element);

                // Step 6: Move to next character
                // Why: Check for digits after element
                i++;

                // Step 7: Extract count (digits after element)
                // Why: Multiply weight by count
                int count = 0;
                while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    count = count * 10 + (formula.charAt(i) - '0');
                    i++;
                }

                // Step 8: If no digit found, count = 1
                // Why: Element without number means one atom
                if (count == 0) {
                    count = 1;
                }

                // Step 9: Add weight × count to total
                // Why: Accumulate molecular weight
                totalWeight += elementWeight * count;
            }
            else {
                // Step 10: Skip unknown characters (safety)
                i++;
            }
        }

        // Step 11: Return total weight
        // Why: Caller needs final result
        return totalWeight;
    }

    /*
    You have given a chemical formula (only Contains C, H, O, digits) U need to tell me its weight, as preceding element are that times -> C: 12, H: 1, O: 8
    Now there coule be brackets (){} etc like c2(k2(CH4)3)2 or C2((CH4))
    C: 12, H: 1, O: 8
     */
    /**
     * APPROACH: Stack-based parsing with bracket handling
     *
     * KEY IDEA:
     * ├─ Use stack to handle nested groups
     * ├─ Each level of nesting gets its own stack entry
     * ├─ When we see '(' → push new group
     * ├─ When we see ')' → pop group, apply multiplier
     * ├─ Element → add weight × count to current group
     *
     * STEPS:
     * 1. Create weight map (C=12, H=1, O=8)
     * 2. Create stack, push 0 (base level)
     * 3. Parse character by character
     * 4. Handle: elements, digits, brackets
     * 5. Return top of stack
     *
     * TIME: O(N) - single pass
     * SPACE: O(D) - D = nesting depth
     */
    public static int getAtomicWeight(String formula) {

        // Step 1: Create weight mapping
        // Why: Quick lookup for element weight
        Map<Character, Integer> weights = new HashMap<>();
        weights.put('C', 12);
        weights.put('H', 1);
        weights.put('O', 8);

        // Step 2: Create stack for nested groups
        // Why: Track sum at each nesting level
        Stack<Integer> stack = new Stack<>();
        stack.push(0);  // Base level (level 0)

        // Step 3: Initialize parsing variables
        int i = 0;
        int n = formula.length();

        // Step 4: Parse formula character by character
        // Why: Build weight incrementally
        while (i < n) {
            char c = formula.charAt(i);

            // Case 1: Opening bracket
            // Why: Start new nested group
            if (c == '(' || c == '{' || c == '[') {
                stack.push(0);  // Push new group sum
                i++;
            }

            // Case 2: Closing bracket
            // Why: End group, apply multiplier
            else if (c == ')' || c == '}' || c == ']') {
                // Step 4.1: Pop current group sum
                int groupSum = stack.pop();

                // Step 4.2: Move past closing bracket
                i++;

                // Step 4.3: Extract multiplier after bracket
                int multiplier = 0;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    multiplier = multiplier * 10 + (formula.charAt(i) - '0');
                    i++;
                }

                // Step 4.4: Default multiplier to 1 if not specified
                if (multiplier == 0) {
                    multiplier = 1;
                }

                // Step 4.5: Add multiplied sum to parent group
                int parentSum = stack.pop();
                stack.push(parentSum + groupSum * multiplier);
            }

            // Case 3: Element character (C, H, O)
            // Why: Add element weight to current group
            else if (c == 'C' || c == 'H' || c == 'O') {
                // Step 4.6: Get element weight
                int elementWeight = weights.get(c);

                // Step 4.7: Move to next character
                i++;

                // Step 4.8: Extract count after element
                int count = 0;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    count = count * 10 + (formula.charAt(i) - '0');
                    i++;
                }

                // Step 4.9: Default count to 1 if not specified
                if (count == 0) {
                    count = 1;
                }

                // Step 4.10: Add element weight × count to current group
                int currentSum = stack.pop();
                stack.push(currentSum + elementWeight * count);
            }

            // Case 4: Unknown element or skip
            // Why: Only care about C, H, O
            else {
                i++;
            }
        }

        // Step 5: Return final weight (top of stack)
        // Why: Stack should have only one element (total)
        return stack.pop();
    }
}
