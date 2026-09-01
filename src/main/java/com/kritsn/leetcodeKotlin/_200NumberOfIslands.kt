package com.kritsn.leetcodeKotlin
/**
 * Problem: Number of Islands
 *
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 */
class _200NumberOfIslands {

    /**
     * Main solution method using Depth-First Search (DFS) approach
     *
     * @param grid 2D character array representing the map
     * @return number of islands found in the grid
     */
    fun numIslands(grid: Array<CharArray>): Int {
        // Edge case: handle empty or null grid
        if (grid.isEmpty() || grid[0].isEmpty()) {
            return 0
        }

        // Store grid dimensions for boundary checking
        val rows = grid.size
        val cols = grid[0].size
        var islandCount = 0

        // Traverse every cell in the grid
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                // When we find unvisited land ('1'), we've discovered a new island
                if (grid[row][col] == '1') {
                    // Increment island counter for this newly discovered island
                    islandCount++

                    // Use DFS to mark all connected land cells as visited
                    // This prevents counting the same island multiple times
                    markIslandAsVisited(grid, row, col, rows, cols)
                }
            }
        }

        return islandCount
    }

    /**
     * Depth-First Search helper method to explore and mark an entire island
     *
     * This method recursively visits all connected land cells (horizontally and vertically)
     * and marks them as visited by changing '1' to '0'
     *
     * @param grid the 2D grid being processed
     * @param row current row position
     * @param col current column position
     * @param rows total number of rows in grid
     * @param cols total number of columns in grid
     */
    private fun markIslandAsVisited(
        grid: Array<CharArray>,
        row: Int,
        col: Int,
        rows: Int,
        cols: Int
    ) {
        // Base case 1: Check if current position is out of bounds
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return
        }

        // Base case 2: Check if current cell is water or already visited
        // '0' represents either water or previously visited land
        if (grid[row][col] == '0') {
            return
        }

        // Mark current land cell as visited by changing it to water ('0')
        // This prevents revisiting the same cell and ensures each land cell
        // is processed exactly once
        grid[row][col] = '0'

        // Recursively explore all four adjacent directions (up, down, left, right)
        // This ensures we mark all connected land cells of the current island

        // Explore upward direction
        markIslandAsVisited(grid, row - 1, col, rows, cols)

        // Explore downward direction
        markIslandAsVisited(grid, row + 1, col, rows, cols)

        // Explore left direction
        markIslandAsVisited(grid, row, col - 1, rows, cols)

        // Explore right direction
        markIslandAsVisited(grid, row, col + 1, rows, cols)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _200NumberOfIslands()

            // Test Case 1: Multiple islands of different shapes
            println("=== Test Case 1: Multiple Islands ===")
            val grid1 = arrayOf(
                charArrayOf('1', '1', '1', '1', '0'),
                charArrayOf('1', '1', '0', '1', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '0', '0', '0')
            )
            println("Input Grid:")
            printGrid(grid1)
            val result1 = solution.numIslands(grid1)
            println("Number of Islands: $result1")
            println("Expected: 1")
            println()

            // Test Case 2: Multiple separate islands
            println("=== Test Case 2: Multiple Separate Islands ===")
            val grid2 = arrayOf(
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '1', '0', '0'),
                charArrayOf('0', '0', '0', '1', '1')
            )
            println("Input Grid:")
            printGrid(grid2)
            val result2 = solution.numIslands(grid2)
            println("Number of Islands: $result2")
            println("Expected: 3")
            println()

            // Test Case 3: Single cell island
            println("=== Test Case 3: Single Cell Cases ===")
            val grid3 = arrayOf(
                charArrayOf('1')
            )
            println("Input Grid:")
            printGrid(grid3)
            val result3 = solution.numIslands(grid3)
            println("Number of Islands: $result3")
            println("Expected: 1")
            println()

            // Test Case 4: No islands (all water)
            println("=== Test Case 4: No Islands ===")
            val grid4 = arrayOf(
                charArrayOf('0', '0', '0'),
                charArrayOf('0', '0', '0'),
                charArrayOf('0', '0', '0')
            )
            println("Input Grid:")
            printGrid(grid4)
            val result4 = solution.numIslands(grid4)
            println("Number of Islands: $result4")
            println("Expected: 0")
        }

        /**
         * Helper method to print the grid in a readable format
         */
        private fun printGrid(grid: Array<CharArray>) {
            for (row in grid) {
                println(row.joinToString(" ") { it.toString() })
            }
        }
    }
}
