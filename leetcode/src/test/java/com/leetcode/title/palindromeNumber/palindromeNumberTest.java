package com.leetcode.title.palindromeNumber;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class palindromeNumberTest {

    private PalindromeNumber palindromeNumber;

    @Before
    public void setUp() throws Exception {
        palindromeNumber = new PalindromeNumber();
    }

    @Test
    public void test_isPalindrome_01() {
        boolean palindrome = palindromeNumber.isPalindrome(121);
        assertTrue(palindrome);
    }

    @Test
    public void test_isPalindrome_01_01() {
        boolean palindrome = palindromeNumber.isPalindrome(-121);
        assertFalse(palindrome);
    }


    @Test
    public void test_isPalindrome_02() {
        boolean palindrome = palindromeNumber.isPalindrome(-101);
        assertFalse(palindrome);
    }

    @Test
    public void test_isPalindrome_03() {
        boolean palindrome = palindromeNumber.isPalindrome(10);
        assertFalse(palindrome);
    }

    @Test
    public void test_isPalindrome_04() {
        boolean palindrome = palindromeNumber.isPalindrome(123);
        assertFalse(palindrome);
    }
}