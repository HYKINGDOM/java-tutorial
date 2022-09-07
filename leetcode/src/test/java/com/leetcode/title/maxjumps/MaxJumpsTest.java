package com.leetcode.title.maxjumps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaxJumpsTest {

    private MaxJumps maxJumps;


    @BeforeEach
    public void init() {
        maxJumps = new MaxJumps();
    }


    @Test
    public void test_main_A() {
        int[] test = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int aInt = 0;
        int profit = maxJumps.maxJumps(test, aInt);
        assertThat(profit).isEqualTo(6);
    }

    @Test
    public void test_main_B() {
        int[] test = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int aInt = 0;
        int profit = maxJumps.maxJumps(test, aInt);
        assertThat(profit).isEqualTo(4);
    }

    @Test
    public void test_main_03() {
        int[] test = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int aInt = 0;
        int profit = maxJumps.maxJumps(test, aInt);
        assertThat(profit).isEqualTo(0);
    }

    @Test
    public void test_main_04() {
        int[] test = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int aInt = 0;
        int profit = maxJumps.maxJumps(test, aInt);
        assertThat(profit).isEqualTo(0);
    }

}