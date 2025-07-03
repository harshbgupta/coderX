package com.kritsn.ivs;

public class Temp {
    public static void main(String[] args) {
        Yulu.convert("aaabccadd");
    }
}

class Yulu {

    /**
     * Input: aaabccadd
     * output: a3bb1c2a1d2
     *
     * @param s
     */
    public static void convert(String s) {
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count += 1;
            } else {
                System.out.print(s.charAt(i) + "" + count);
                count = 1;
            }

        }

    }
}

