package com.leetcode.hw.arraymaximumsum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.leetcode.hw.arraymaximumsum.ArrayMaximumSum.initMethod;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArrayMaximumSumTest {


    private ArrayMaximumSum arrayMaximumSum;

    @BeforeEach
    public void init() {
        arrayMaximumSum = new ArrayMaximumSum();
    }

    @Test
    public void test_demo_01() {

        int first = 5;
        int[] ints = new int[]{-2, -8, -1, -5, -9};
        int initMethod = initMethod(first, ints);
        assertThat(initMethod).isEqualTo(-1);
    }

    @Test
    public void test_demo_02() {

        int first = 8;
        int[] ints = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
        int initMethod = initMethod(first, ints);
        assertThat(initMethod).isEqualTo(18);
    }

    @Test
    public void test_demo_03() {

        int first = 5;
        int[] ints = new int[]{-1, -2, -3, -4, 5};
        int initMethod = initMethod(first, ints);
        assertThat(initMethod).isEqualTo(5);
    }
}