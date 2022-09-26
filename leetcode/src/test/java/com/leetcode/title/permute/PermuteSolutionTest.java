package com.leetcode.title.permute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PermuteSolutionTest {

    private PermuteSolution permuteSolution;


    @BeforeEach
    public void init() {
        permuteSolution = new PermuteSolution();
    }


    @Test
    public void test_main_A() {
        int[] test = new int[]{1, 2, 3};
        List<List<Integer>> profit = permuteSolution.permute(test);

        for (List<Integer> integerList : profit) {
            System.out.println(Arrays.toString(integerList.toArray()));
        }
        assertThat(profit.size()).isEqualTo(6);
    }

}