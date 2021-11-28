package com.java.util.javautil.leetcode.zigzagConversion;

public class zigzagConversion {

    public String convert(String s, int numRows) {
        if (s.length() == 1) {
            return s;
        }

        char[] chars = s.toCharArray();

        int length = s.length();

        int num = length / numRows;
        int numR = length % numRows;

        int strLength = 0;
        if (numR < numRows) {
            strLength = num * (numRows - 1) + 1;
        }else {
            strLength = num * (numRows - 1) + (numR - numRows);
        }
        char[][] characters = new char[strLength][numRows];


        for (int i = 0; i < chars.length; i++) {
            for (int n = 0; n < strLength; n++) {
                for (int j = 0; j < numRows; j++) {



                    characters[n][j] = chars[i];
                }
            }
        }


        return "";
    }
}
