package com.kritsn.ivs;

public class AiDash {

    public static void main(String[] args) {
        String[] strs = {"Prashantika", "Prave", "Priyanshu"};

        String common = "";
        String tempCommon = null;
        for (int i = 0; i < strs.length-1; i++) {
            if (tempCommon == null)
                tempCommon = strs[i];
            String s2 = strs[i + 1];
            int length = tempCommon.length();
            if (tempCommon.length() > s2.length()) {
                length = s2.length();
            }
            for (int j = 0; j < length; j++) {
                if (tempCommon.charAt(j) == s2.charAt(j)) {
                    common += tempCommon.charAt(j) + "";
                } else {
                    tempCommon = common;
                    break;
                }
            }
        }

    }
}





/**
 *  i     j     tempCommon         s2              common
 *  0          Prashant            Pravesh         ""
 *        0                                         P
 *        1                                         Pr
 *        2                                         Pra
 *        3     Pra                                 Pra    -----> Break out inside loop
 *
 *
 *  1           Pra                Priyanshu        Pra
 *        0                                         P
 *        1                                         Pr  -----> Break out inside loop
 *        2    Pr
 *  2                                              -----> Break out outer loop
 *
 *
 *          O(n2)  --- O(nl)   n number of element
 *                             l number of
 *
 *
 */
