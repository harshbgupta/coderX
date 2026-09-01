package com.kritsn.leetcodeKotlin
import java.util.Stack

///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 19, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * 71. Simplify Path
 * You are given an absolute path for a Unix-style file system, which always begins with a slash '/'.
 * Your task is to transform this absolute path into its simplified canonical path.
 */
class _071SimplifyPath {

    /**
     * stack.add(part) → acts as push
     * stack.removeAt(stack.size - 1) → acts as pop
     * At the end, stack.joinToString("/") reconstructs the path.
     */

    /**
     * 🧠 Algorithm & Approach:
     * 1. Split the input path by "/" to extract each component.
     * 2. Use a stack to keep track of valid directory names.
     *    - If the part is "." or empty, skip it.
     *    - If the part is "..", pop the last directory from the stack if available.
     *    - Otherwise, push the directory name onto the stack.
     * 3. Finally, join all elements in the stack with "/" and prefix with a leading "/".
     *
     * Time Complexity: O(n), where n is the length of the path string.
     * Space Complexity: O(n), for the stack storing valid directories.
     */
    fun simplifyPath(path: String): String {
        val stack = Stack<String>()

        // Split the path by '/' to handle each component
        val parts = path.split("/")

        for (part in parts) {
            when {
                part.isEmpty() || part == "." -> {
                    // Skip empty or current directory symbols
                    continue
                }
                part == ".." -> {
                    // Move one directory up if possible
                    if (stack.isNotEmpty()) stack.pop()
                }
                else -> {
                    // Valid directory name
                    stack.push(part)
                }
            }
        }

        // Construct canonical path
        return "/" + stack.joinToString("/")
    }

    /**
     * Same approach but using the array.
     *
     * stack.add(part) → acts as push
     * stack.removeAt(stack.size - 1) → acts as pop
     * At the end, stack.joinToString("/") reconstructs the path.
     */
    fun simplifyPathUsingArray(path: String): String {
        val stack = mutableListOf<String>()

        // Split the path by '/' to handle each component
        val parts = path.split("/")

        for (part in parts) {
            when {
                part.isEmpty() || part == "." -> {
                    // Skip empty or current directory symbols
                    continue
                }
                part == ".." -> {
                    // Move one directory up if possible
                    if (stack.isNotEmpty()) stack.removeAt(stack.size - 1)
                }
                else -> {
                    // Valid directory name
                    stack.add(part)
                }
            }
        }

        // Construct canonical path
        return "/" + stack.joinToString("/")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = _071SimplifyPath()

            // Test Case 1
            val path1 = "/home/"
            println("Input: $path1")
            println("Output: ${solution.simplifyPath(path1)}")
            println("Expected: /home\n")

            // Test Case 2
            val path2 = "/../"
            println("Input: $path2")
            println("Output: ${solution.simplifyPath(path2)}")
            println("Expected: /\n")

            // Test Case 3
            val path3 = "/home//foo/"
            println("Input: $path3")
            println("Output: ${solution.simplifyPath(path3)}")
            println("Expected: /home/foo\n")

            // Test Case 4
            val path4 = "/a/./b/../../c/"
            println("Input: $path4")
            println("Output: ${solution.simplifyPath(path4)}")
            println("Expected: /c\n")

            // Test Case 5
            val path5 = "/a//b////c/d//././/.."
            println("Input: $path5")
            println("Output: ${solution.simplifyPath(path5)}")
            println("Expected: /a/b/c\n")
        }
    }
}
