package com.kritsn.leetcodeKotlin.medium
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 16, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Determine if a 9 x 9 Sudoku board is valid.
 *
 * Rules:
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each 3 x 3 sub‑box must contain the digits 1-9 without repetition.
 *
 * The board may be partially filled; only the filled cells are validated.
 */
class _036ValidSudoku {

    ///////////////////////////////////////////////////////////////////////////
    //https://youtu.be/HyiAKwasi3M?feature=shared
    // Single Scan with Row, Column, and Box Sets:
    // The board may contain '.' representing empty cells, which are ignored during validation.

    // We iterate over every cell. For a digit we:
    // - Check if it already exists in its row set.
    // - Check if it already exists in its column set.
    // - Check if it already exists in its 3×3 box set.
    // If any check fails, the board is invalid.
    //
    // 🪜 Steps:
    // 1. Prepare three arrays of HashSet<Char> (rows, cols, boxes), size 9 each.
    // 2. For each cell (r, c):
    //    a. Skip if '.'.
    //    b. Compute box index = (r / 3) * 3 + (c / 3).
    //    c. If digit in rows[r] / cols[c] / boxes[boxIdx] -> return false.
    //    d. Else add digit to the three sets.
    // 3. Return true if no rule is violated.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(81) ~ O(1) — constant work for a 9×9 board.
    // Space Complexity: O(27) ~ O(1) — fixed number of HashSets (rows, cols, boxes).
    ///////////////////////////////////////////////////////////////////////////
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        // Step 1: Create arrays of sets for rows, columns, and boxes
        val rows = Array(9) { HashSet<Char>() }
        val cols = Array(9) { HashSet<Char>() }
        val boxes = Array(9) { HashSet<Char>() }

        // Step 2: Iterate over every cell
        for (r in 0 until 9) {
            for (c in 0 until 9) {
                val ch = board[r][c]
                if (ch == '.') continue // Skip empty cells

                val boxIdx = (r / 3) * 3 + (c / 3) // Which 3×3 sub‑box

                // Check for duplicates in row, column, or box
                // if any row or col or box has same 'ch' the it's mean it's not valid
                if (ch in rows[r] || ch in cols[c] || ch in boxes[boxIdx]) {
                    return false // Violation found, or not valid
                }

                // Add digit to respective sets, set won't allow duplicate
                rows[r].add(ch)
                cols[c].add(ch)
                boxes[boxIdx].add(ch)
            }
        }
        return true // No violations → valid board
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _036ValidSudoku()

            val board1 = arrayOf(
                charArrayOf('5','3','.','.','7','.','.','.','.'),
                charArrayOf('6','.','.','1','9','5','.','.','.'),
                charArrayOf('.','9','8','.','.','.','.','6','.'),
                charArrayOf('8','.','.','.','6','.','.','.','3'),
                charArrayOf('4','.','.','8','.','3','.','.','1'),
                charArrayOf('7','.','.','.','2','.','.','.','6'),
                charArrayOf('.','6','.','.','.','.','2','8','.'),
                charArrayOf('.','.','.','4','1','9','.','.','5'),
                charArrayOf('.','.','.','.','8','.','.','7','9')
            )
            println("Test Case 1: Valid Board -> ${solver.isValidSudoku(board1)}") // Expected: true

            val board2 = arrayOf(
                charArrayOf('8','3','.','.','7','.','.','.','.'),
                charArrayOf('6','.','.','1','9','5','.','.','.'),
                charArrayOf('.','9','8','.','.','.','.','6','.'),
                charArrayOf('8','.','.','.','6','.','.','.','3'),
                charArrayOf('4','.','.','8','.','3','.','.','1'),
                charArrayOf('7','.','.','.','2','.','.','.','6'),
                charArrayOf('.','6','.','.','.','.','2','8','.'),
                charArrayOf('.','.','.','4','1','9','.','.','5'),
                charArrayOf('.','.','.','.','8','.','.','7','9')
            )
            println("Test Case 2: Invalid Board (duplicate 8) -> ${solver.isValidSudoku(board2)}") // Expected: false
        }
    }
}