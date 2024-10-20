package com.leetcode.title.palindromeNumber;

/**
 * @author meta
 */
public class PalindromeNumberNoConvertString {

    public boolean isPalindrome(int x) {

        if (x == 0) {
            return true;
        }

        if (x < 0 || x % 10 == 0) {
            return false;
        }

        int finInt = x;
        int i = 0;
        int n = 0;
        int num = 0;

        while (x > 0) {
            x = x / 10;
            n++;
        }

        x = finInt;
        n = n - 1;
        while (x > 0) {
            i = x % 10;
            num = (int)(num + Math.pow(10, n) * i);
            n--;
            x = x / 10;
        }
        return num - finInt == 0;
    }
}
