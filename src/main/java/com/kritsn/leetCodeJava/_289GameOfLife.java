package com.kritsn.leetCodeJava;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * According to the rules of Conway's Game of Life,
 * update the board to the next state **in-place**.
 */
public class _289GameOfLife {

    ///////////////////////////////////////////////////////////////////////////
    // https://youtu.be/lEeJyUW-xLc?feature=shared
    // In-place Encoding Method:
    // We solve this problem by encoding state transitions directly into the grid.
    //
    // 💡 Key Idea:
    // Since we need to apply updates simultaneously to all cells, we can't update
    // the board directly based on neighbors that might have already changed.
    //
    // ➕ Solution:
    // - Encode intermediate states using special values:
    //     - 1  → 0 (alive → dead) is marked as -1
    //     - 0  → 1 (dead → alive) is marked as  2
    //
    // This allows us to:
    // - Count live neighbors by checking original values via abs() (abs(-1) = 1)
    // - Finalize transitions in a second pass
    //
    // 🪜 Steps:
    // 1. Iterate the entire grid
    // 2. Count live neighbors using the original values
    // 3. Apply the game rules and mark transitions using -1 or 2
    // 4. After the board is updated, do a final pass to convert:
    //      - -1 → 0
    //      -  2 → 1
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(m * n) — each cell is visited twice (one for update, one for cleanup)
    // Space Complexity: O(1) — done entirely in-place, no extra data structures used
    ///////////////////////////////////////////////////////////////////////////
    void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // Define all 8 directions (horizontal, vertical, diagonal)
        int[][] directions = {
                {-1, -1}, // top-left
                {-1, 0},  // top
                {-1, 1},  // top-right
                {0, -1},  // left
                {0, 1},   // right
                {1, -1},  // bottom-left
                {1, 0},   // bottom
                {1, 1}    // bottom-right
        };

        // First pass: Apply rules and mark cell transitions using in-place encoding
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                int liveNeighbors = 0;

                // Count number of live neighbors by looking at 8 directions
                for (int[] dir : directions) {
                    int nr = r + dir[0]; // new row
                    int nc = c + dir[1]; // new col

                    // Check boundaries of the board
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                        // Use absolute value because cell might be marked -1 or 2
                        // -1: was 1, now 0 — still alive in the original state
                        //  2: was 0, now 1 — still dead in the original state
                        if (Math.abs(board[nr][nc]) == 1) {
                            liveNeighbors++;
                        }
                    }
                }

                // Apply Game of Life rules:
                if (board[r][c] == 1) {
                    // Rule 1 or Rule 3: live cell dies (underpopulation or overpopulation)
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[r][c] = -1; // Mark as alive → dead
                    }
                } else if (board[r][c] == 0) {
                    // Rule 4: dead cell becomes alive (exactly 3 live neighbors)
                    if (liveNeighbors == 3) {
                        board[r][c] = 2; // Mark as dead → alive
                    }
                }
            }
        }

        // Second pass: Finalize the state of each cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Restore final state:
                // - Cells marked as 2 → alive (1)
                // - Cells marked as -1 → dead (0)
                board[r][c] = board[r][c] > 0 ? 1 : 0;
            }
        }
    }

    // 🔍 Main method with clearly labeled test cases
    public static void main(String[] args) {
        _289GameOfLife solver = new _289GameOfLife();

        int[][] board1 = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        System.out.println("Test Case 1: Before update");
        for (int[] row : board1) System.out.println(java.util.Arrays.toString(row));

        solver.gameOfLife(board1);

        System.out.println("Test Case 1: After update");
        for (int[] row : board1) System.out.println(java.util.Arrays.toString(row));
        // Expected:
        // [0,0,0]
        // [1,0,1]
        // [0,1,1]
        // [0,1,0]

        int[][] board2 = {
                {1, 1},
                {1, 0}
        };
        System.out.println("\nTest Case 2: Before update");
        for (int[] row : board2) System.out.println(java.util.Arrays.toString(row));

        solver.gameOfLife(board2);

        System.out.println("Test Case 2: After update");
        for (int[] row : board2) System.out.println(java.util.Arrays.toString(row));
        // Expected:
        // [1,1]
        // [1,1]
    }
}
