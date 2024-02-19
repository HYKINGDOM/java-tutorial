package com.leetcode.title.maxarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaxAreaATest {

    private MaxAreaA maxArea;

    @BeforeEach
    public void init_class() {
        maxArea = new MaxAreaA();
    }

    @Test
    public void test_main_01() {
        int[] ints = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int profit = maxArea.maxArea(ints);
        assertThat(profit).isEqualTo(49);
    }

    @Test
    public void test_main_02() {
        int[] ints = {1, 1};
        int profit = maxArea.maxArea(ints);
        assertThat(profit).isEqualTo(1);
    }

    @Test
    public void test_main_03() {
        int[] ints = {4, 3, 2, 1, 4};
        int profit = maxArea.maxArea(ints);
        assertThat(profit).isEqualTo(16);
    }

    @Test
    public void test_main_04() {
        int[] ints = {1, 2, 1};
        int profit = maxArea.maxArea(ints);
        assertThat(profit).isEqualTo(2);
    }
}