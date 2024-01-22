package com.leetcode.title.palindromeNumber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeNumberNoConvertStringTest {


    private PalindromeNumberNoConvertString palindromeNumberNoConvertString;

    @BeforeEach
    public void setUp() throws Exception {
        palindromeNumberNoConvertString = new PalindromeNumberNoConvertString();
    }

    @Test
    public void test_palindromeNumberNoConvertString_01() {
        boolean palindrome = palindromeNumberNoConvertString.isPalindrome(121);
        assertTrue(palindrome);
    }

    @Test
    public void test_palindromeNumberNoConvertString_02() {
        boolean palindrome = palindromeNumberNoConvertString.isPalindrome(-121);
        assertFalse(palindrome);
    }

    @Test
    public void test_palindromeNumberNoConvertString_03() {
        System.out.println(12.10/10);
        System.out.println(12.10%10);
    }


    @Test
    public void test_palindromeNumberNoConvertString_04() {
        boolean palindrome = palindromeNumberNoConvertString.isPalindrome(10);
        assertFalse(palindrome);
    }

    @Test
    public void test_palindromeNumberNoConvertString_05() {
        boolean palindrome = palindromeNumberNoConvertString.isPalindrome(101);
        assertTrue(palindrome);
    }

    @Test
    public void test_palindromeNumberNoConvertString_06() {
        boolean palindrome = palindromeNumberNoConvertString.isPalindrome(-101);
        assertFalse(palindrome);
    }

    @Test
    public void test_palindromeNumberNoConvertString_07() {
        boolean palindrome = palindromeNumberNoConvertString.isPalindrome(0);
        assertTrue(palindrome);
    }


    @Test
    public void test_palindromeNumberNoConvertString_08() {
        boolean palindrome = palindromeNumberNoConvertString.isPalindrome(123);
        assertFalse(palindrome);
    }

    @Test
    public void test_palindromeNumberNoConvertString_09() {
        boolean palindrome = palindromeNumberNoConvertString.isPalindrome(2147483647);
        assertFalse(palindrome);
    }
}