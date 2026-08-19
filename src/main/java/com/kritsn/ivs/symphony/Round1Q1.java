package com.kritsn.ivs.symphony;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 18, 2026
 */

/*
String pattern = "abba";
    String str = "dog cat cat dog";

Question: funtion accetps 2 strings pattern and snetence, need to return boolean if sentence foloow the same parrtend like forst and last charanter repeating in pattern so first nad last word is reptaing in sentense, (true) else false

 */
public class Round1Q1 {


    public static void main(String[] args) {
        String pattern = "abbad";
        String sentence = "dog cat cat dog dog";
        System.out.println(patternChecker(pattern, sentence));
    }

    public static boolean patternChecker(String pattern, String sentence) {
        String[] chars = pattern.split("");
        String[] words = sentence.split(" ");

        if(chars.length!=words.length) return false;

        Map charToWord = new HashMap();
        Map wordToChar = new HashMap();
        for (int i = 0; i<chars.length; i++){
            String c = chars[i];
            String word = words[i];

            //char mapping to word
            if(charToWord.containsKey(c)){
                if(!charToWord.get(c).equals(word)){
                    return false;
                }
            } else {
                charToWord.put(c, word);
            }

            //word mapping to char
            if(wordToChar.containsKey(word)){
                if(!wordToChar.get(word).equals(c)){
                    return false;
                }
            } else {
                wordToChar.put(word, c);
            }
        }
        return true;
    }
}
