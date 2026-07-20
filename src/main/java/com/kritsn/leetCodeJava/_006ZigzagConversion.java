package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * <p>
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 */
public class _006ZigzagConversion {

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
    String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        // Create a list of rows to simulate the zigzag
        StringBuilder[] rows = new StringBuilder[Math.min(numRows, s.length())];
        for (int i = 0; i < rows.length; i++) rows[i] = new StringBuilder();

        int currentRow = 0;
        boolean goingDown = false;

        // Traverse through the string and distribute characters to rows
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);

            // Change direction when reaching top or bottom
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            // Move to the next row depending on direction
            currentRow += goingDown ? 1 : -1;
        }

        // Combine all rows into the final string
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) result.append(row);
        return result.toString();
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _006ZigzagConversion solver = new _006ZigzagConversion();

        String s1 = "PAYPALISHIRING";
        int numRows1 = 3;
        System.out.println("Test Case 1: Input = \"" + s1 + "\", Rows = " + numRows1 + " -> Output = \"" + solver.convert(s1, numRows1) + "\""); // Expected: "PAHNAPLSIIGYIR"

        String s2 = "PAYPALISHIRING";
        int numRows2 = 4;
        System.out.println("Test Case 2: Input = \"" + s2 + "\", Rows = " + numRows2 + " -> Output = \"" + solver.convert(s2, numRows2) + "\""); // Expected: "PINALSIGYAHRPI"

        String s3 = "A";
        int numRows3 = 1;
        System.out.println("Test Case 3: Input = \"" + s3 + "\", Rows = " + numRows3 + " -> Output = \"" + solver.convert(s3, numRows3) + "\""); // Expected: "A"

        String s4 = "AB";
        int numRows4 = 1;
        System.out.println("Test Case 4: Input = \"" + s4 + "\", Rows = " + numRows4 + " -> Output = \"" + solver.convert(s4, numRows4) + "\""); // Expected: "AB"

        String s5 = "ABCDEFGHIJKLMN";
        int numRows5 = 5;
        System.out.println("Test Case 5: Input = \"" + s5 + "\", Rows = " + numRows5 + " -> Output = \"" + solver.convert(s5, numRows5) + "\""); // Custom test
    }
}
