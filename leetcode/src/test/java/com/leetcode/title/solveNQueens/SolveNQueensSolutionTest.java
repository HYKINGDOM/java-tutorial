package com.leetcode.title.solveNQueens;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SolveNQueensSolutionTest {


    private SolveNQueensSolution solveNQueensSolution;


    private char[][] chars;

    @BeforeEach
    public void init() {
        solveNQueensSolution = new SolveNQueensSolution();
    }

    private void init_char_array(int n) {
        chars = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(chars[i], '.');
        }
    }


    @Test
    public void test_main_1() {
        int n = 10;
        List<List<String>> profit = solveNQueensSolution.solveNQueens(n);

        List<List<String>> result = Lists.newArrayList(
                Lists.newArrayList(".Q..", "...Q", "Q...", "..Q.")
                , Lists.newArrayList("..Q.", "Q...", "...Q", ".Q.."));

        assertThat(profit.size()).isEqualTo(1);
        assertThat(profit).isEqualTo(result);
    }

    @Test
    public void test_main_2() {
        int n = 10;
        List<List<String>> profit = solveNQueensSolution.solveNQueens(n);

        List<String> stringArrayList = Lists.newArrayList("Q");
        List<List<String>> result = new ArrayList<>();
        result.add(stringArrayList);

        assertThat(profit.size()).isEqualTo(1);
        assertThat(profit).isEqualTo(result);
    }


    @Test
    public void test_judgeDiagonalLeftLine_1() {
        init_char_array(4);
        chars[0][2] = 'Q';
        boolean b = solveNQueensSolution.judgeDiagonalLeftLine(chars, 1, 1, 4);
        System.out.println(b);
    }


}