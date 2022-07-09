package com.leetcode.title;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringToIntTest {

    private StringToInt stringToInt;

    @Before
    public void init_class() {
        stringToInt = new StringToInt();
    }

    @Test
    public void test_case_only_num() {
        String caseString = "42";
        int num = stringToInt.myAtoi(caseString);
        assertEquals(42, num);
    }

    @Test
    public void test_case_contain_empty() {
        String caseString = "   -42";
        int num = stringToInt.myAtoi(caseString);
        assertEquals(-42, num);
    }

    @Test
    public void test_case_contain_letter_end() {
        String caseString = "4193 with words";
        int num = stringToInt.myAtoi(caseString);
        assertEquals(4193, num);
    }

    @Test
    public void test_case_contain_letter_start() {
        String caseString = "words and 987";
        int num = stringToInt.myAtoi(caseString);
        assertEquals(0, num);
    }

    @Test
    public void test_case_contain_minus_start() {
        String caseString = "-91283472332";
        int num = stringToInt.myAtoi(caseString);
        assertEquals(-2147483648, num);
    }

    @Test
    public void test_case_contain_point() {
        String caseString = "3.14159";
        int num = stringToInt.myAtoi(caseString);
        assertEquals(3, num);
    }

    @Test
    public void test_case_contain_plus() {
        String caseString = "+1";
        int num = stringToInt.myAtoi(caseString);
        assertEquals(1, num);
    }

    @Test
    public void test_case_contain_plus_and_minus() {
        String caseString = "+-12";
        int num = stringToInt.myAtoi(caseString);
        assertEquals(0, num);
    }

    @Test
    public void test_case_contain_zero_and_minus() {
        String caseString = "00000-42a1234";
        int num = stringToInt.myAtoi(caseString);
        assertEquals(0, num);
    }


    @Test
    public void test_case_contain_zero_and_minus_001() {
        String caseString = "00000-42a1234";
        int num = stringToInt.myAtoi(caseString);
        assertEquals(0, num);
    }

}