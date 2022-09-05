package com.leetcode.hw.longestcontinuoussubarraywithpositiveproduct;

import org.junit.jupiter.api.Test;

import static com.leetcode.hw.longestcontinuoussubarraywithpositiveproduct.LongeStcontinuousSubarray.initLongArrayMethod;
import static org.assertj.core.api.Assertions.assertThat;

class LongeStcontinuousSubarrayTest {


    @Test
    public void test_demo_01() {

        int first = 5;
        int[] ints = new int[]{1, 2, 3, -5, 1};
        int initMethod = initLongArrayMethod(first, ints);
        assertThat(initMethod).isEqualTo(3);
    }

    @Test
    public void test_demo_02() {

        int first = 5;
        int[] ints = new int[]{1, 2, 3, 0, 5};
        int initMethod = initLongArrayMethod(first, ints);
        assertThat(initMethod).isEqualTo(3);
    }

    @Test
    public void test_demo_03() {
        int first = 10;
        int[] ints = new int[]{8, 11, 2, -10, 18, 10, 11, 17, 24, -4};
        int initMethod = initLongArrayMethod(first, ints);
        assertThat(initMethod).isEqualTo(10);
    }
}