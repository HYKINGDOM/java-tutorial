package com.leetcode.hw.nkw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestCTest {


    private TestC testC;


    @BeforeEach
    public void init() {
        testC = new TestC();
    }


    @Test
    public void test_main_C() {
        assertThat(4).isEqualTo(4);
    }


}