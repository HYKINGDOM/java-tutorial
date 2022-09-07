package com.leetcode.title.profit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaxProfitTwoTest {
    private MaxProfitTwo maxProfitTwo;


    @BeforeEach
    public void init() {
        maxProfitTwo = new MaxProfitTwo();
    }


    @Test
    public void test_main_A() {
        int[] test = new int[]{7, 1, 5, 3, 6, 4};
        int profit = maxProfitTwo.maxProfit(test);
        assertThat(profit).isEqualTo(7);
    }

    @Test
    public void test_main_B() {
        int[] test = new int[]{1, 2, 3, 4, 5};
        int profit = maxProfitTwo.maxProfit(test);
        assertThat(profit).isEqualTo(4);
    }

    @Test
    public void test_main_03() {
        int[] test = new int[]{7, 6, 4, 3, 1};
        int profit = maxProfitTwo.maxProfit(test);
        assertThat(profit).isEqualTo(0);
    }
}