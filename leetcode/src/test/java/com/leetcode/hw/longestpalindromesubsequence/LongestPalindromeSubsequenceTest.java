package com.leetcode.hw.longestpalindromesubsequence;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import static com.leetcode.hw.longestpalindromesubsequence.LongestPalindromeSubsequence.initArrayMethodLongestPalindromeSubsequence;
import static org.assertj.core.api.Assertions.assertThat;

class LongestPalindromeSubsequenceTest {

    @Test
    void initArrayMethod_01() {
        String num = "babad";

        String result = initArrayMethodLongestPalindromeSubsequence(num);
        //assertThat(result.length()).isEqualTo(3);
        assertThat(result).isEqualTo("bab");
    }


    @Test
    void initArrayMethod_02() {
        String num = "cbbd";
        String result = initArrayMethodLongestPalindromeSubsequence(num);
        //assertThat(result.length()).isEqualTo(2);
        assertThat(result).isEqualTo("bb");
    }

    @Test
    void initArrayMethod_03() {
        String num = "ccc";
        String result = initArrayMethodLongestPalindromeSubsequence(num);
        //assertThat(result.length()).isEqualTo(3);
        assertThat(result).isEqualTo("ccc");
    }

    @Test
    void initArrayMethod_04() {
        String num = "aaaa";
        String result = initArrayMethodLongestPalindromeSubsequence(num);
        //assertThat(result.length()).isEqualTo(3);
        assertThat(result).isEqualTo("aaaa");
    }

    @Test
    void initArrayMethod_05() {
        String num = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

        long timeMillis = System.currentTimeMillis();
        String result = initArrayMethodLongestPalindromeSubsequence(num);
        long stopTime = System.currentTimeMillis() - timeMillis;

        System.out.println(stopTime);
        //assertThat(result.length()).isEqualTo(3);
        assertThat(result).isEqualTo(num);
    }
}