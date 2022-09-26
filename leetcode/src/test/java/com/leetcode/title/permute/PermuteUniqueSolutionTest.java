package com.leetcode.title.permute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PermuteUniqueSolutionTest {

    private PermuteUniqueSolution permuteSolution;

    private PermuteUniqueSolutionA permuteUniqueSolutionA;


    @BeforeEach
    public void init() {
        permuteSolution = new PermuteUniqueSolution();
        permuteUniqueSolutionA = new PermuteUniqueSolutionA();
    }


    @Test
    public void test_main_A() {
        int[] test = new int[]{1, 1, 2};
        List<List<Integer>> profit = permuteSolution.permuteUnique(test);

        for (List<Integer> integerList : profit) {
            System.out.println(Arrays.toString(integerList.toArray()));
        }
        assertThat(profit.size()).isEqualTo(3);
    }


    @Test
    public void test_main_1() {
        int[] test = new int[]{1, 1, 2};
        List<List<Integer>> profit = permuteUniqueSolutionA.permuteUnique(test);

        for (List<Integer> integerList : profit) {
            System.out.println(Arrays.toString(integerList.toArray()));
        }
        assertThat(profit.size()).isEqualTo(3);
    }

}