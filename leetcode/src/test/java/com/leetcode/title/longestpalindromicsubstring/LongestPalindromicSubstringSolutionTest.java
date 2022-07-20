package com.leetcode.title.longestpalindromicsubstring;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class LongestPalindromicSubstringSolutionTest {
    private LongestPalindromicSubstringSolution solution;

    @Before
    public void init_data(){
        solution = new LongestPalindromicSubstringSolution();
    }
    @Test
    public void test_example_1(){
        String str = "babad";
        String longestPalindrome = solution.longestPalindrome(str);
        assertEquals("bab",longestPalindrome);
    }

    @Test
    public void test_example_2(){
        String str = "cbbd";
        String longestPalindrome = solution.longestPalindrome(str);
        assertEquals("bb",longestPalindrome);
    }
    @Test
    public void test_example_3(){
        String str = "a";
        String longestPalindrome = solution.longestPalindrome(str);
        assertEquals("a",longestPalindrome);
    }
    @Test
    public void test_example_4(){
        String str = "ac";
        String longestPalindrome = solution.longestPalindrome(str);
        assertEquals("a",longestPalindrome);
    }
    @Test
    public void test_example_5(){
        String str = "aacabdkacaa";
        String longestPalindrome = solution.longestPalindrome(str);
        Assertions.assertEquals("aca", longestPalindrome);
    }
    @Test
    public void test_example_6(){
        String str = "aacabdkacaa";
        String longestPalindrome = solution.longestPalindrome(str);
        assertEquals("aca",longestPalindrome);
    }
}
