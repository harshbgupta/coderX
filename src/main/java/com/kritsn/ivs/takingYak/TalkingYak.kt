package com.kritsn.ivs.takingYak

class Robot(
    private var x: Int, // The current x-coordinate of the robot
    private var y: Int, // The current y-coordinate of the robot
    private var orientation: Char, // The current orientation of the robot (N, E, S, W)
    private val gridMaxX: Int, // The maximum x-coordinate of the grid
    private val gridMaxY: Int // The maximum y-coordinate of the grid
) {
    // List of cardinal directions in clockwise order
    private val directions = listOf('N', 'E', 'S', 'W')

    /**
     * Moves the robot forward in the direction it is currently facing
     */
    private fun move() {
        when (orientation) {
            'N' -> if (y < gridMaxY) y++ // Move north if within grid boundaries
            'E' -> if (x < gridMaxX) x++ // Move east if within grid boundaries
            'S' -> if (y > 0) y-- // Move south if within grid boundaries
            'W' -> if (x > 0) x-- // Move west if within grid boundaries
        }
    }

    /**
     * Turns the robot 90 degrees to the left
     */
    private fun turnLeft() {
        val currentIndex = directions.indexOf(orientation) // Get current orientation index
        orientation = directions[(currentIndex - 1 + directions.size) % directions.size] // Calculate new orientation
    }

    /**
     * Turns the robot 90 degrees to the right
     */
    private fun turnRight() {
        val currentIndex = directions.indexOf(orientation) // Get current orientation index
        orientation = directions[(currentIndex + 1) % directions.size] // Calculate new orientation
    }

    /**
     * Processes a series of instructions (L, R, M) to navigate the robot
     */
    fun processInstructions(instructions: String) {
        instructions.forEach { instruction ->
            when (instruction) {
                'M' -> move() // Move forward
                'L' -> turnLeft() // Turn left
                'R' -> turnRight() // Turn right
            }
        }
    }

    /**
     * Returns the current position and orientation of the robot as a string
     */
    override fun toString(): String {
        return "$x $y $orientation"
    }
}

/**
 * Main function to start the program
 */
fun main() {
    runTestCases()
}

/**
 * Prompt user to take required inputs
 */
fun runTestCases() {
    try {
        // Prompt user to enter the grid's upper-right corner coordinates
        println("Enter the upper-right grid coordinates (e.g., 5 5):")
        val (gridMaxX, gridMaxY) = readln().split(" ").map { it.toInt() }


        // Prompt user to enter the robot's initial position and orientation
        println("Enter the robot's starting position (e.g., 1 2 N):")
        val (x, y, orientation) = readln().split(" ").let { Triple(it[0].toInt(), it[1].toInt(), it[2][0]) }

        // Prompt user to enter the navigation instructions
        println("Enter the navigation instructions (e.g., LMLMLMLMM):")
        val instructions = readln()

        // Initialize the robot with the given position, orientation, and grid boundaries
        val robot = Robot(x, y, orientation, gridMaxX, gridMaxY)

        // Process the instructions to navigate the robot
        robot.processInstructions(instructions)

        // Print the final position and orientation of the robot
        println(robot)
    } catch (e: Exception) {
        // Print warning for Wrong Input
        println("Incorrect Input, Please mind the \"SPACE\" and TRY AGAIN...")
        println("-----------------------------------------------------------------------------------")
        runTestCases() //Running the program again, as provided input was incorrect
    }
}