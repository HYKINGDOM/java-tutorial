package com.leetcode.title.stringtointeger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringToIntegerTest {

    private StringToInteger stringToInteger;

    @BeforeEach
    public void init() {
        stringToInteger = new StringToInteger();
    }

    @Test
    public void test_main_01() {
        String str = "42";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(42);
    }

    @Test
    public void test_main_02() {
        String str = "   -42";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(-42);
    }

    @Test
    public void test_main_03() {
        String str = "4193 with words";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(4193);
    }

    @Test
    public void test_main_04() {
        String str = "words and 987";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void test_main_05() {
        String str = "-91283472332";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(Integer.MIN_VALUE);
    }

    @Test
    public void test_main_06() {

        String str = "3.14159";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(3);
    }

    @Test
    public void test_main_07() {

        String str = "";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void test_main_08() {

        String str = "1";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(1);
    }

    @Test
    public void test_main_09() {

        String str = "+1";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(1);
    }

    @Test
    public void test_main_10() {

        String str = "+-1";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void test_main_11() {

        String str = "00000-42a1234";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void test_main_12() {
        String str = ".1";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void test_main_13() {
        String str = "   +0 123";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void test_main_14() {
        String str = "20000000000000000000";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    public void test_main_15() {
        String str = "  0000000000012345678";
        int profit = stringToInteger.myAtoi(str);
        assertThat(profit).isEqualTo(12345678);
    }
}