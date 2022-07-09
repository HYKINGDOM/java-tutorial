package com.leetcode.title.palindromeNumber;

public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        char[] charArray = String.valueOf(x).toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length/2; i++) {
            if (charArray[i] != charArray[charArray.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}
