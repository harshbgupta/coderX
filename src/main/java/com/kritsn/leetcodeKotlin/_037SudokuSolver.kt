package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 16, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Each digit 1-9 must appear exactly once in each row, column, and 3x3 subgrid.
 */
class _037SudokuSolver {

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
    fun solveSudoku(board: Array<CharArray>) {
        solve(board)
    }

    private fun solve(board: Array<CharArray>): Boolean {
        for (row in 0 until 9) {
            for (col in 0 until 9) {
                if (board[row][col] == '.') {
                    // Try all digits 1–9
                    for (digit in '1'..'9') {
                        if (isValid(board, row, col, digit)) {
                            board[row][col] = digit
                            if (solve(board)) return true // Recurse
                            board[row][col] = '.' // Backtrack
                        }
                    }
                    return false // No valid digit found → backtrack
                }
            }
        }
        return true // All cells filled
    }

    // Check if placing 'digit' at (row, col) is valid
    private fun isValid(board: Array<CharArray>, row: Int, col: Int, digit: Char): Boolean {
        for (i in 0 until 9) {
            // Check row
            if (board[row][i] == digit) return false
            // Check column
            if (board[i][col] == digit) return false
            // Check 3x3 subgrid
            val boxRow = 3 * (row / 3) + i / 3
            val boxCol = 3 * (col / 3) + i % 3
            if (board[boxRow][boxCol] == digit) return false
        }
        return true
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val board = arrayOf(
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

            println("Original Sudoku Board:")
            board.forEach { println(it.concatToString()) }

            val solver = _037SudokuSolver()
            solver.solveSudoku(board)

            println("\nSolved Sudoku Board:")
            board.forEach { println(it.concatToString()) }
        }

    }
}