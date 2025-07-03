package com.kritsn.scaler.dsa.intrermediate2;

import java.util.Arrays;

public class StringsDataStructureCC {
    public static void main(String[] args) {
        String s = "dabbaedbb";
        q2SortString(s);
    }

    /**
     * Ques: Toggle the character in a string in uppercase and lowercase
     * <p>
     * TC:
     * SC:
     */
    private static void q1ToggleCaseOptimsied() {
        String s = "asaahjAEDAklaADEEWRsasaKLksaLl";


    }

    /**
     * Given a String, contains only lowercase character. TASK:Sort the string
     * i.e.: s= "dabbaedbb" ans S = "aabbde"
     * TC:
     * SC:
     */
    private static void q2SortString(String s) {
        String[] arrOfS = s.split("");
        Arrays.sort(arrOfS);
        for (String charString : arrOfS) {
            System.out.print(charString);
        }
        System.out.println();
        System.out.println(arrOfS);

    }
}
