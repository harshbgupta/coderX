package com.kritsn.leetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 14, 2025
///////////////////////////////////////////////////////////////////////////

/**
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
class _006ZigzagConversion {

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
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1 || s.length <= numRows) return s

        // Create a list of rows to simulate the zigzag
        val rows = MutableList(minOf(numRows, s.length)) { StringBuilder() }

        var currentRow = 0
        var goingDown = false

        // Traverse through the string and distribute characters to rows
        for (char in s) {
            rows[currentRow].append(char)

            // Change direction when reaching top or bottom
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown
            }

            // Move to the next row depending on direction
            currentRow += if (goingDown) 1 else -1
        }

        // Combine all rows into the final string
        return rows.joinToString("") { it.toString() }
    }
}

// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _006ZigzagConversion()

    val s1 = "PAYPALISHIRING"
    val numRows1 = 3
    println("Test Case 1: Input = \"$s1\", Rows = $numRows1 -> Output = \"${solver.convert(s1, numRows1)}\"") // Expected: "PAHNAPLSIIGYIR"

    val s2 = "PAYPALISHIRING"
    val numRows2 = 4
    println("Test Case 2: Input = \"$s2\", Rows = $numRows2 -> Output = \"${solver.convert(s2, numRows2)}\"") // Expected: "PINALSIGYAHRPI"

    val s3 = "A"
    val numRows3 = 1
    println("Test Case 3: Input = \"$s3\", Rows = $numRows3 -> Output = \"${solver.convert(s3, numRows3)}\"") // Expected: "A"

    val s4 = "AB"
    val numRows4 = 1
    println("Test Case 4: Input = \"$s4\", Rows = $numRows4 -> Output = \"${solver.convert(s4, numRows4)}\"") // Expected: "AB"

    val s5 = "ABCDEFGHIJKLMN"
    val numRows5 = 5
    println("Test Case 5: Input = \"$s5\", Rows = $numRows5 -> Output = \"${solver.convert(s5, numRows5)}\"") // Custom test
}
