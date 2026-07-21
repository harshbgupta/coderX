package com.kritsn.leetCodeJava.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 20, 2026
 */

/**
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly
 * maxWidth characters and is fully (left and right) justified.
 */
public class _068TextJustification {

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
    List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;

        while (index < words.length) {
            int lineLen = words[index].length();
            int last = index + 1;

            // Pack as many words as fit into maxWidth
            while (last < words.length) {
                if (lineLen + 1 + words[last].length() > maxWidth) break;
                lineLen += 1 + words[last].length();
                last++;
            }

            List<String> lineWords = new ArrayList<>(List.of(words).subList(index, last));
            int wordsLength = lineWords.stream().mapToInt(String::length).sum();
            int spacesNeeded = maxWidth - wordsLength;

            StringBuilder line = new StringBuilder();
            if (last == words.length || lineWords.size() == 1) {
                // Last line or single-word line → left justify
                line.append(String.join(" ", lineWords));
                int remaining = maxWidth - line.length();
                line.append(" ".repeat(Math.max(0, remaining)));
            } else {
                // Fully justify the line
                int spacesBetweenWords = lineWords.size() - 1;
                int evenSpace = spacesNeeded / spacesBetweenWords;
                int extraSpace = spacesNeeded % spacesBetweenWords;

                for (int i = 0; i < lineWords.size(); i++) {
                    line.append(lineWords.get(i));
                    if (i < spacesBetweenWords) {
                        int spaces = evenSpace + (i < extraSpace ? 1 : 0);
                        line.append(" ".repeat(spaces));
                    }
                }
            }

            result.add(line.toString());
            index = last;
        }

        return result;
    }

    public static void main(String[] args) {
        _068TextJustification solver = new _068TextJustification();

        String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth1 = 16;
        System.out.println("Test Case 1:");
        solver.fullJustify(words1, maxWidth1).forEach(it -> System.out.println("\"" + it + "\""));

        System.out.println("\nTest Case 2:");
        String[] words2 = {"What", "must", "be", "acknowledgment", "shall", "be"};
        int maxWidth2 = 16;
        solver.fullJustify(words2, maxWidth2).forEach(it -> System.out.println("\"" + it + "\""));

        System.out.println("\nTest Case 3:");
        String[] words3 = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain",
                "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        int maxWidth3 = 20;
        solver.fullJustify(words3, maxWidth3).forEach(it -> System.out.println("\"" + it + "\""));
    }
}
