package com.kritsn.leetCodeJava.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * 71. Simplify Path
 * You are given an absolute path for a Unix-style file system, which always begins with a slash '/'.
 * Your task is to transform this absolute path into its simplified canonical path.
 */
public class _071SimplifyPath {

    /**
     * stack.push(part) → acts as push
     * stack.pop() → acts as pop
     * At the end, joining the stack with "/" reconstructs the path.
     */

    /**
     * 🧠 Algorithm & Approach:
     * 1. Split the input path by "/" to extract each component.
     * 2. Use a stack to keep track of valid directory names.
     * - If the part is "." or empty, skip it.
     * - If the part is "..", pop the last directory from the stack if available.
     * - Otherwise, push the directory name onto the stack.
     * 3. Finally, join all elements in the stack with "/" and prefix with a leading "/".
     * <p>
     * Time Complexity: O(n), where n is the length of the path string.
     * Space Complexity: O(n), for the stack storing valid directories.
     */
    String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        // Split the path by '/' to handle each component
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                // Skip empty or current directory symbols
                continue;
            } else if (part.equals("..")) {
                // Move one directory up if possible
                if (!stack.isEmpty()) stack.pop();
            } else {
                // Valid directory name
                stack.push(part);
            }
        }

        // Construct canonical path
        return "/" + String.join("/", stack);
    }

    /**
     * Same approach but using the array.
     * <p>
     * stack.add(part) → acts as push
     * stack.remove(stack.size() - 1) → acts as pop
     * At the end, joining the stack with "/" reconstructs the path.
     */
    String simplifyPathUsingArray(String path) {
        List<String> stack = new ArrayList<>();

        // Split the path by '/' to handle each component
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                // Skip empty or current directory symbols
                continue;
            } else if (part.equals("..")) {
                // Move one directory up if possible
                if (!stack.isEmpty()) stack.remove(stack.size() - 1);
            } else {
                // Valid directory name
                stack.add(part);
            }
        }

        // Construct canonical path
        return "/" + String.join("/", stack);
    }

    public static void main(String[] args) {
        _071SimplifyPath solution = new _071SimplifyPath();

        // Test Case 1
        String path1 = "/home/";
        System.out.println("Input: " + path1);
        System.out.println("Output: " + solution.simplifyPath(path1));
        System.out.println("Expected: /home\n");

        // Test Case 2
        String path2 = "/../";
        System.out.println("Input: " + path2);
        System.out.println("Output: " + solution.simplifyPath(path2));
        System.out.println("Expected: /\n");

        // Test Case 3
        String path3 = "/home//foo/";
        System.out.println("Input: " + path3);
        System.out.println("Output: " + solution.simplifyPath(path3));
        System.out.println("Expected: /home/foo\n");

        // Test Case 4
        String path4 = "/a/./b/../../c/";
        System.out.println("Input: " + path4);
        System.out.println("Output: " + solution.simplifyPath(path4));
        System.out.println("Expected: /c\n");

        // Test Case 5
        String path5 = "/a//b////c/d//././/..";
        System.out.println("Input: " + path5);
        System.out.println("Output: " + solution.simplifyPath(path5));
        System.out.println("Expected: /a/b/c\n");
    }
}
