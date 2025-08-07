package com.kritsn.jLeetCode;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 05, 2025
 */

/*
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 */
public class _006ZigZagConversion {


    ///////////////////////////////////////////////////////////////////////////
    // Zigzag Simulation with Direction Tracking:
    //
    // We simulate writing the characters in a zigzag pattern using a list of
    // StringBuilders. The direction flips when we reach either the top or bottom.
    //
    // 🪜 Steps:
    // 1. If numRows == 1, return the original string.
    // 2. Create a list of StringBuilders, one for each row.
    // 3. Track current row and direction.
    // 4. For each character in the input:
    //    a. Append it to the current row.
    //    b. Change direction if top or bottom is reached.
    // 5. Concatenate all rows to build the final result.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n) — iterate through each character once.
    // Space Complexity: O(n) — store characters in row buffers.
    ///////////////////////////////////////////////////////////////////////////
    private String zigZagConversion(String s, int numRows) {
        if (numRows == 1) return s;

        // Initialize StringBuilders for each row
        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        // Initialize each StringBuilder
        for (int i = 0; i < numRows; i++) stringBuilders[i] = new StringBuilder();

        // Track current row and direction
        boolean goingDown = false;
        int currentRow = 0;
        // For each character in the input:
        for (char c : s.toCharArray()) {
            // Append it to the current row.
            stringBuilders[currentRow].append(c);
            // Change direction if top or bottom is reached.
            if (currentRow == 0 || currentRow == numRows - 1) goingDown = !goingDown;
            // Move to the next row
            currentRow += goingDown ? 1 : -1;
        }
        // Concatenate all rows to build the final result.
        StringBuilder finalResult = new StringBuilder();
        for (StringBuilder stringBuilder : stringBuilders) finalResult.append(stringBuilder);
        // Return the final result.
        return finalResult.toString();
    }

    public static void main(String[] args) {
        _006ZigZagConversion solution = new _006ZigZagConversion();
        System.out.println("ZigZag Conversion: " + solution.zigZagConversion("PAYPALISHIRING", 3));
    }
}
