package com.leetcode.title.intToRoman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntToRomanTest {

    private IntToRoman intToRoman;

    @BeforeEach
    public void init_class() {
        intToRoman = new IntToRoman();
    }

    @Test
    public void test_main_01() {
        int ints = 3;
        String profit = intToRoman.intToRoman(ints);
        assertThat(profit).isEqualTo("III");
    }

    @Test
    public void test_main_02() {
        String str = "I" + "I" + "I";
        System.out.println(str);
    }

}