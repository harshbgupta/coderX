package com.kritsn.leetCodeJava;

import java.util.HashSet;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 06, 2025
 */

public class _036ValidSudoku {

    private boolean isValidSudoku(char[][] board) {

        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] column = new HashSet[9];
        HashSet<Character>[] box = new HashSet[9];

        //init all row and cols to empty
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            column[i] = new HashSet<>();
            box[i] = new HashSet<>();
        }

        //step 1: iterate all element
        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int colIndex = 0; colIndex < 9; colIndex++) {
                //skip empty entry
                char currentDigit = board[rowIndex][colIndex];
                System.out.println("currentDigit: " + currentDigit);
                if (currentDigit == '.') continue;
                int boxIndex = rowIndex / 3 * 3 + colIndex / 3;
                System.out.println("boxIndex: " + boxIndex);
                if (!rows[rowIndex].add(currentDigit) || !column[colIndex].add(currentDigit) || !box[boxIndex].add(currentDigit)) {
                    return false; //conflict
                }

            }
        }
        return true; //no conflict
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
        _036ValidSudoku solution = new _036ValidSudoku();
        boolean result = solution.isValidSudoku(board);
        System.out.println("Is Sudoku Valid: " + result); //expected = true
    }
}
