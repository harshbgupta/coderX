package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Determine if a 9 x 9 Sudoku board is valid.
 * <p>
 * Rules:
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each 3 x 3 sub‑box must contain the digits 1-9 without repetition.
 * <p>
 * The board may be partially filled; only the filled cells are validated.
 */
public class _036ValidSudoku {

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
    // 1. Prepare three arrays of HashSet<Character> (rows, cols, boxes), size 9 each.
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
    @SuppressWarnings("unchecked")
    boolean isValidSudoku(char[][] board) {
        // Step 1: Create arrays of sets for rows, columns, and boxes
        java.util.HashSet<Character>[] rows = new java.util.HashSet[9];
        java.util.HashSet<Character>[] cols = new java.util.HashSet[9];
        java.util.HashSet<Character>[] boxes = new java.util.HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new java.util.HashSet<>();
            cols[i] = new java.util.HashSet<>();
            boxes[i] = new java.util.HashSet<>();
        }

        // Step 2: Iterate over every cell
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue; // Skip empty cells

                int boxIdx = (r / 3) * 3 + (c / 3); // Which 3×3 sub‑box

                // Check for duplicates in row, column, or box
                // if any row or col or box has same 'ch' the it's mean it's not valid
                if (rows[r].contains(ch) || cols[c].contains(ch) || boxes[boxIdx].contains(ch)) {
                    return false; // Violation found, or not valid
                }

                // Add digit to respective sets, set won't allow duplicate
                rows[r].add(ch);
                cols[c].add(ch);
                boxes[boxIdx].add(ch);
            }
        }
        return true; // No violations → valid board
    }

    public static void main(String[] args) {
        _036ValidSudoku solver = new _036ValidSudoku();

        char[][] board1 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("Test Case 1: Valid Board -> " + solver.isValidSudoku(board1)); // Expected: true

        char[][] board2 = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("Test Case 2: Invalid Board (duplicate 8) -> " + solver.isValidSudoku(board2)); // Expected: false
    }
}
