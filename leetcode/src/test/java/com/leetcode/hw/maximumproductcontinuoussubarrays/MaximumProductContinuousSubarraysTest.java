package com.leetcode.hw.maximumproductcontinuoussubarrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.leetcode.hw.maximumproductcontinuoussubarrays.MaximumProductContinuousSubarrays.initArrayMethod;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MaximumProductContinuousSubarraysTest {


    private MaximumProductContinuousSubarrays maximumProductContinuousSubarrays;


    @BeforeEach
    public void init_class() {
        maximumProductContinuousSubarrays = new MaximumProductContinuousSubarrays();
    }

    @Test
    public void test_max_array_01() {
        int num = 3;
        int[] ints = {-3, 0, -2};
        int result = initArrayMethod(num, ints);
        assertThat(result).isEqualTo(0);
    }


    @Test
    public void test_max_array_02() {
        int num = 4;
        int[] ints = {3, 2, -2, 4};
        int result = initArrayMethod(num, ints);
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void test_max_array_03() {
        int num = 1;
        int[] ints = {4};
        int result = initArrayMethod(num, ints);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void test_max_array_04() {
        int num = 3;
        int[] ints = {-3, 2, -2};
        int result = initArrayMethod(num, ints);
        assertThat(result).isEqualTo(12);
    }

    @Test
    public void test_max_array_05() {
        int num = 3;
        int[] ints = {-3, -2, -2};
        int result = initArrayMethod(num, ints);
        assertThat(result).isEqualTo(6);
    }

}