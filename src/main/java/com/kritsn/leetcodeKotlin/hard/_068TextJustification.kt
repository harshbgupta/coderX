package com.kritsn.leetcodeKotlin.hard
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 14, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly
 * maxWidth characters and is fully (left and right) justified.
 */
class _068TextJustification {

    ///////////////////////////////////////////////////////////////////////////
    // Greedy Packing + Space Distribution:
    //
    // We iterate over words and fill lines greedily. When a line is full:
    // - If it's the last line → left-justify.
    // - Else → distribute spaces evenly.
    //
    // 🪜 Steps:
    // 1. Iterate over words and keep packing until lineLen + numWords - 1 exceeds maxWidth.
    // 2. Distribute spaces:
    //    - If only one word, pad right with spaces.
    //    - Else, divide spaces between words, and place extras to the left.
    // 3. Handle the last line separately with left justification.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n * maxWidth), where n is the number of words.
    // Space Complexity: O(n * maxWidth) for storing the result lines.
    ///////////////////////////////////////////////////////////////////////////
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val result = mutableListOf<String>()
        var index = 0

        while (index < words.size) {
            var lineLen = words[index].length
            var last = index + 1

            // Pack as many words as fit into maxWidth
            while (last < words.size) {
                if (lineLen + 1 + words[last].length > maxWidth) break
                lineLen += 1 + words[last].length
                last++
            }

            val lineWords = words.slice(index until last)
            val spacesNeeded = maxWidth - lineWords.sumOf { it.length }

            val line = StringBuilder()
            if (last == words.size || lineWords.size == 1) {
                // Last line or single-word line → left justify
                line.append(lineWords.joinToString(" "))
                val remaining = maxWidth - line.length
                repeat(remaining) { line.append(' ') }
            } else {
                // Fully justify the line
                val spacesBetweenWords = lineWords.size - 1
                val evenSpace = spacesNeeded / spacesBetweenWords
                val extraSpace = spacesNeeded % spacesBetweenWords

                for ((i, word) in lineWords.withIndex()) {
                    line.append(word)
                    if (i < spacesBetweenWords) {
                        repeat(evenSpace + if (i < extraSpace) 1 else 0) {
                            line.append(' ')
                        }
                    }
                }
            }

            result.add(line.toString())
            index = last
        }

        return result
    }
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _068TextJustification()

            val words1 = arrayOf("This", "is", "an", "example", "of", "text", "justification.")
            val maxWidth1 = 16
            println("Test Case 1:")
            solver.fullJustify(words1, maxWidth1).forEach { println("\"$it\"") }

            println("\nTest Case 2:")
            val words2 = arrayOf("What","must","be","acknowledgment","shall","be")
            val maxWidth2 = 16
            solver.fullJustify(words2, maxWidth2).forEach { println("\"$it\"") }

            println("\nTest Case 3:")
            val words3 = arrayOf("Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do")
            val maxWidth3 = 20
            solver.fullJustify(words3, maxWidth3).forEach { println("\"$it\"") }
        }
    }
}
