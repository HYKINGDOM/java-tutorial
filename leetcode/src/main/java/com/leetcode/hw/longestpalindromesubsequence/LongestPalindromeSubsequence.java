package com.leetcode.hw.longestpalindromesubsequence;

import java.util.Scanner;

public class LongestPalindromeSubsequence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String strNext = in.next();
        String initMethod = initArrayMethodLongestPalindromeSubsequence(strNext);
        System.out.println(initMethod.length());
    }

    public static String initArrayMethodLongestPalindromeSubsequence(String s) {

        int num = 0;
        char[] chars = s.toCharArray();
        String result = String.valueOf(chars[0]);
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (i < j) {
                    if (chars[i] == chars[j] && (j - i) > 1) {
                        if (completeResult(i, j, chars) && (j - i) > num) {
                            num = j - i;
                            result = s.substring(i, j + 1);
                        }
                    } else if (chars[i] == chars[j] && (j - i) == 1) {
                        if (s.substring(i, j + 1).length() > result.length()) {
                            result = s.substring(i, j + 1);
                            num = 1;
                        }
                    }
                }
            }
        }

        return result;
    }

    public static boolean completeResult(int i, int j, char[] chars) {
        if (chars[i] != chars[j] || i > j) {
            return false;
        }

        if (i == j) {
            return true;
        }

        if ((j - i) == 1 && chars[i] == chars[j]) {
            return true;
        }

        return completeResult(i + 1, j - 1, chars);
    }
}
