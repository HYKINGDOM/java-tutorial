package com.leetcode.title.reverseString;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseStringTest {

    private ReverseString reverseString;

    @Before
    public void init_method() {
        reverseString = new ReverseString();
    }

    @Test
    public void test_reverse_string_01() {
        int reverse = reverseString.reverse(123);
        assertEquals(reverse, 321);
    }

    @Test
    public void test_reverse_string_02() {
        int reverse = reverseString.reverse(-123);
        assertEquals(reverse, -321);
    }

    @Test
    public void test_reverse_string_03(){
        int reverse = reverseString.reverse(120);
        assertEquals(reverse, 21);
    }

    @Test
    public void test_reverse_string_04(){
        int reverse = reverseString.reverse(0);
        assertEquals(reverse, 0);
    }

    @Test
    public void test_reverse_string_06(){
        int reverse = reverseString.reverse(1534236469);
        assertEquals(reverse, 0);
    }

}