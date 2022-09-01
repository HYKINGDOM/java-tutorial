package com.leetcode.hw.stringmerge;

import com.leetcode.hw.nkw.TestC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;

class StringMergeTest {


    @Test
    public void test_main_C() {
        String str = "dec fab";

        String trim = str.trim();
        Map<Integer, String> integerStringMap = StringMerge.getIntegerStringMap(trim);

        String result = integerStringMap.values().stream().reduce((a, b) -> a + b).get();

        assertThat(result).isEqualTo("abcedf");
    }

    @Test
    public void test_main_demo_2() {
        String str = "ab CD";

        String trim = str.trim();
        Map<Integer, String> integerStringMap = StringMerge.getIntegerStringMap(trim);

        String result = integerStringMap.values().stream().reduce((a, b) -> a + b).get();

        assertThat(result).isEqualTo("CDab");
    }


    @Test
    public void test_main_merge() {
        int odd = 7;
        int even = 6;

        System.out.println(odd%2);
        System.out.println(even%2);
    }

}