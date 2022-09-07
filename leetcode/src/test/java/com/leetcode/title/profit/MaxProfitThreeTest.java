package com.leetcode.title.profit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaxProfitThreeTest {

    private MaxProfitThree maxProfitThree;


    @BeforeEach
    public void init() {
        maxProfitThree = new MaxProfitThree();
    }


    @Test
    public void test_main_A() {
        int[] test = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int profit = maxProfitThree.maxProfit(test);
        assertThat(profit).isEqualTo(6);
    }

    @Test
    public void test_main_B() {
        int[] test = new int[]{1, 2, 3, 4, 5};
        int profit = maxProfitThree.maxProfit(test);
        assertThat(profit).isEqualTo(4);
    }

    @Test
    public void test_main_03() {
        int[] test = new int[]{7, 6, 4, 3, 1};
        int profit = maxProfitThree.maxProfit(test);
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void test_main_04() {
        int[] test = new int[]{1};
        int profit = maxProfitThree.maxProfit(test);
        assertThat(profit).isEqualTo(0);
    }
}