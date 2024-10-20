package com.leetcode.title.longestpalindromicsubstring;

/**
 * @author meta
 */
public class LongestPalindromicSubstringSolution {

    public static void main(String[] args) {
        System.out.println(3 / 2);
    }

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] charArray = s.toCharArray();
        if (s.length() == 2) {
            if (charArray[0] == charArray[1]) {
                return s;
            } else {
                return s.substring(0, 1);
            }
        }
        String output = null;
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray.length; j++) {
                int length = j - i;
                if (charArray[i] == charArray[j] && length >= 1) {
                    String flag = null;
                    for (int k = 0; k <= length; k++) {
                        if (charArray[i + k] == charArray[j - k]) {
                            String substring = s.substring(i, j + 1);
                            if (flag == null) {
                                flag = substring;
                            }
                            if (flag.length() < substring.length()) {
                                flag = substring;
                            }
                        } else {
                            continue;
                        }

                    }
                }
            }
        }
        return output;
    }
}
