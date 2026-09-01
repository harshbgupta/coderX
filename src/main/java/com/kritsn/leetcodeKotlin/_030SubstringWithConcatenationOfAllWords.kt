package com.kritsn.leetcodeKotlin
///////////////////////////////////////////////////////////////////////////
//           Copyright © 2025 Kritsn LLP. All rights reserved.
//                      @author Radhey (hr-sh)
//                      @since Jul 16, 2025
///////////////////////////////////////////////////////////////////////////

/**
 * You are given a string s and an array of strings words. All the strings of words are of the same length.
 * A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
 * Return an array of the starting indices of all the concatenated substrings in s.
 */
class _030SubstringWithConcatenationOfAllWords {

    ///////////////////////////////////////////////////////////////////////////
    // Sliding Window + Frequency Map:
    //
    // We look for substrings of total length = wordLen * wordCount
    // For each index, extract word-sized chunks and validate if they match the word map
    //
    // 🪜 Steps:
    // 1. Build a frequency map of words.
    // 2. Loop over each possible offset (0 to wordLen-1) to support alignment.
    // 3. Within that, slide a window and count frequencies of words within it.
    // 4. If matched, add the starting index to result.
    //
    // ⏱ Time & Space Complexity
    // Time Complexity: O(n * m) where n = length of s, m = length of each word.
    // Space Complexity: O(k) where k = number of words in `words`.
    ///////////////////////////////////////////////////////////////////////////
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val result = mutableListOf<Int>()
        if (words.isEmpty() || s.isEmpty()) return result

        val wordLen = words[0].length
        val wordCount = words.size
        val totalLen = wordLen * wordCount

        if (s.length < totalLen) return result

        // Build frequency map of the words
        val wordMap = mutableMapOf<String, Int>()
        for (word in words) {
            wordMap[word] = wordMap.getOrDefault(word, 0) + 1
        }

        // Try every offset from 0 to wordLen - 1
        for (i in 0 until wordLen) {
            var left = i
            var count = 0
            val windowMap = mutableMapOf<String, Int>()

            for (j in i..s.length - wordLen step wordLen) {
                if (j + wordLen > s.length) break

                val word = s.substring(j, j + wordLen)

                if (word in wordMap) {
                    windowMap[word] = windowMap.getOrDefault(word, 0) + 1
                    count++

                    // Shrink window if frequency exceeds desired count
                    while (windowMap[word]!! > wordMap[word]!!) {
                        val leftWord = s.substring(left, left + wordLen)
                        windowMap[leftWord] = windowMap[leftWord]!! - 1
                        left += wordLen
                        count--
                    }

                    // If all words matched, store the index
                    if (count == wordCount) {
                        result.add(left)
                    }
                } else {
                    // Reset window if word not found
                    windowMap.clear()
                    count = 0
                    left = j + wordLen
                }
            }
        }

        return result
    }
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = _030SubstringWithConcatenationOfAllWords()

            val s1 = "barfoothefoobarman"
            val words1 = arrayOf("foo", "bar")
            println("Test Case 1: s = \"$s1\", words = ${words1.contentToString()} -> Indices = ${solver.findSubstring(s1, words1)}") // Expected: [0, 9]

            val s2 = "wordgoodgoodgoodbestword"
            val words2 = arrayOf("word", "good", "best", "word")
            println("Test Case 2: s = \"$s2\", words = ${words2.contentToString()} -> Indices = ${solver.findSubstring(s2, words2)}") // Expected: []

            val s3 = "barfoofoobarthefoobarman"
            val words3 = arrayOf("bar", "foo", "the")
            println("Test Case 3: s = \"$s3\", words = ${words3.contentToString()} -> Indices = ${solver.findSubstring(s3, words3)}") // Expected: [6,9,12]

            val s4 = "lingmindraboofooowingdingbarrwingmonkeypoundcake"
            val words4 = arrayOf("fooo","barr","wing","ding","wing")
            println("Test Case 4: s = \"$s4\", words = ${words4.contentToString()} -> Indices = ${solver.findSubstring(s4, words4)}") // Expected: [13]

            val s5 = "aaaaaaaaaaaaaa"
            val words5 = arrayOf("aa","aa")
            println("Test Case 5: s = \"$s5\", words = ${words5.contentToString()} -> Indices = ${solver.findSubstring(s5, words5)}") // Expected: [0,1,2,3,4,5,6,7,8,9,10]

        }

    }
}