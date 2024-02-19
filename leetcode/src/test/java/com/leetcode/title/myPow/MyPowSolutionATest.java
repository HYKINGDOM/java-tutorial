package com.leetcode.title.myPow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyPowSolutionATest {
    private MyPowSolutionA myPowSolution;

    @BeforeEach
    public void init() {
        myPowSolution = new MyPowSolutionA();
    }

    @Test
    public void test_main_1() {
        double x = 2.00000;
        int n = 10;
        double profit = myPowSolution.myPow(x, n);
        assertThat(profit).isEqualTo(1024.00000);
    }

    @Test
    public void test_main_2() {
        double x = 2.10000;
        int n = 3;
        double profit = myPowSolution.myPow(x, n);
        assertThat(profit).isEqualTo(9.26100);
    }

    @Test
    public void test_main_3() {
        double x = 2.00000;
        int n = -2;
        double profit = myPowSolution.myPow(x, n);
        assertThat(profit).isEqualTo(0.25000);
    }

    @Test
    public void test_main_4() {
        double x = 0.44528;
        int n = 0;
        double profit = myPowSolution.myPow(x, n);
        assertThat(profit).isEqualTo(1.0);
    }

    @Test
    public void test_main_5() {
        double x = 2.00000;
        int n = -2147483648;
        double profit = myPowSolution.myPow(x, n);
        assertThat(profit).isEqualTo(0.0);
    }

    @Test
    public void test_main_6() {
        double x = 0.44894;
        int n = -5;
        double profit = myPowSolution.myPow(x, n);
        assertThat(profit).isEqualTo(54.83508);
    }
}