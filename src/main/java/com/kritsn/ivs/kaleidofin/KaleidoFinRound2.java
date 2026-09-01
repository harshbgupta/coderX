package com.kritsn.ivs.kaleidofin;

import com.kritsn.utils.TreeNode;
import com.kritsn.utils.TreePrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 17, 2026
 */


/*
Ques 1:
String input = "    I   am Harsh   ";

String output = "I ma hsraH";

remove spaces at the beg/end of the string
replace multiple spaces with single space betn the words
-- Do not use any inbuild methods expect toCharArray, chatAt, length
 */
public class KaleidoFinRound2 {

    public static void main(String[] args) {
        String input = "    I   am Harsh   ";
        List<String> words = getWords(input);

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<words.size();i++){
            String reverseWord = reverse(words.get(i));
            sb.append(reverseWord);
            if (i!= words.size()-1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    private static List<String> getWords(String input) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                StringBuilder sb = new StringBuilder();
                sb.append(input.charAt(i));
                boolean nextNonSpace = true;
                while (nextNonSpace){
                    i++;
                    if (input.charAt(i) == ' ') nextNonSpace = false;
                    else {
                        sb.append(input.charAt(i));
                    }
                }
                words.add(sb.toString());
            }
        }
        return words;
    }

    public static String reverse(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i= word.length()-1; i>=0; i--){
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }


}
