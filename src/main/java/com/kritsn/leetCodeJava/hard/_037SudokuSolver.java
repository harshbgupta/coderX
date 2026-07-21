package com.kritsn.leetCodeJava.hard;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Each digit 1-9 must appear exactly once in each row, column, and 3x3 subgrid.
 */
public class _037SudokuSolver {

    ///////////////////////////////////////////////////////////////////////////
    // Backtracking + Constraint Validation:
    //
    // We try placing digits from 1-9 in each empty cell. If a placement violates
    // Sudoku rules (row/column/box), we skip it. If valid, we place and recurse.
    // If no solution is found from that state, we backtrack.
    //
    // 🪜 Steps:
    // 1. Scan the board to find the first empty cell ('.').
    // 2. Try digits 1–9 at that cell:
    //    a. Check if it's valid (no row/col/box conflicts).
    //    b. If valid → place and recurse.
    //    c. If solve fails → backtrack.
    // 3. When no empty cells remain, solution is complete.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(9^(n)) — where n is number of empty cells.
    // Space Complexity: O(n) — recursion stack for n empty cells.
    ///////////////////////////////////////////////////////////////////////////
    void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    // Try all digits 1–9
                    for (char digit = '1'; digit <= '9'; digit++) {
                        if (isValid(board, row, col, digit)) {
                            board[row][col] = digit;
                            if (solve(board)) return true; // Recurse
                            board[row][col] = '.'; // Backtrack
                        }
                    }
                    return false; // No valid digit found → backtrack
                }
            }
        }
        return true; // All cells filled
    }

    // Check if placing 'digit' at (row, col) is valid
    private boolean isValid(char[][] board, int row, int col, char digit) {
        for (int i = 0; i < 9; i++) {
            // Check row
            if (board[row][i] == digit) return false;
            // Check column
            if (board[i][col] == digit) return false;
            // Check 3x3 subgrid
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == digit) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
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

        System.out.println("Original Sudoku Board:");
        for (char[] row : board) System.out.println(new String(row));

        _037SudokuSolver solver = new _037SudokuSolver();
        solver.solveSudoku(board);

        System.out.println("\nSolved Sudoku Board:");
        for (char[] row : board) System.out.println(new String(row));
    }
}
