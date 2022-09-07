package com.leetcode.title.profit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaxProfitTest {

    private MaxProfit maxProfit;


    @BeforeEach
    public void init() {
        maxProfit = new MaxProfit();
    }


    @Test
    public void test_main_A() {
        int[] test = new int[]{7, 1, 5, 3, 6, 4};
        int profit = maxProfit.maxProfit(test);
        assertThat(profit).isEqualTo(5);
    }

    @Test
    public void test_main_B() {
        int[] test = new int[]{7, 6, 4, 3, 1};
        int profit = maxProfit.maxProfit(test);
        assertThat(profit).isEqualTo(0);
    }

}