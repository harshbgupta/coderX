package com.kritsn.kLeetcode

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 16, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * According to the rules of Conway's Game of Life,
 * update the board to the next state **in-place**.
 */
class _289GameOfLife {

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
    fun gameOfLife(board: Array<IntArray>) {
        val rows = board.size
        val cols = board[0].size

        // Define all 8 directions (horizontal, vertical, diagonal)
        val directions = listOf(
            intArrayOf(-1, -1), // top-left
            intArrayOf(-1, 0), // top
            intArrayOf(-1, 1), // top-right
            intArrayOf(0, -1), // left
            intArrayOf(0, 1),  // right
            intArrayOf(1, -1), // bottom-left
            intArrayOf(1, 0), // bottom
            intArrayOf(1, 1)  // bottom-right
        )

        // First pass: Apply rules and mark cell transitions using in-place encoding
        for (r in 0 until rows) {
            for (c in 0 until cols) {

                var liveNeighbors = 0

                // Count number of live neighbors by looking at 8 directions
                for (dir in directions) {
                    val nr = r + dir[0] // new row
                    val nc = c + dir[1] // new col

                    // Check boundaries of the board
                    if (nr in 0 until rows && nc in 0 until cols) {
                        // Use absolute value because cell might be marked -1 or 2
                        // -1: was 1, now 0 — still alive in the original state
                        //  2: was 0, now 1 — still dead in the original state
                        if (kotlin.math.abs(board[nr][nc]) == 1) {
                            liveNeighbors++
                        }
                    }
                }

                // Apply Game of Life rules:
                if (board[r][c] == 1) {
                    // Rule 1 or Rule 3: live cell dies (underpopulation or overpopulation)
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[r][c] = -1 // Mark as alive → dead
                    }
                } else if (board[r][c] == 0) {
                    // Rule 4: dead cell becomes alive (exactly 3 live neighbors)
                    if (liveNeighbors == 3) {
                        board[r][c] = 2 // Mark as dead → alive
                    }
                }
            }
        }

        // Second pass: Finalize the state of each cell
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                // Restore final state:
                // - Cells marked as 2 → alive (1)
                // - Cells marked as -1 → dead (0)
                board[r][c] = when {
                    board[r][c] > 0 -> 1
                    else -> 0
                }
            }
        }
    }
}


// 🔍 Main method with clearly labeled test cases
fun main() {
    val solver = _289GameOfLife()

    val board1 = arrayOf(
        intArrayOf(0, 1, 0),
        intArrayOf(0, 0, 1),
        intArrayOf(1, 1, 1),
        intArrayOf(0, 0, 0)
    )
    println("Test Case 1: Before update")
    board1.forEach { println(it.contentToString()) }

    solver.gameOfLife(board1)

    println("Test Case 1: After update")
    board1.forEach { println(it.contentToString()) }
    // Expected:
    // [0,0,0]
    // [1,0,1]
    // [0,1,1]
    // [0,1,0]


    val board2 = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, 0)
    )
    println("\nTest Case 2: Before update")
    board2.forEach { println(it.contentToString()) }

    solver.gameOfLife(board2)

    println("Test Case 2: After update")
    board2.forEach { println(it.contentToString()) }
    // Expected:
    // [1,1]
    // [1,1]
}
