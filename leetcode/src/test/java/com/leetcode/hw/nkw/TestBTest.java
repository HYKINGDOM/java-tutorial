package com.leetcode.hw.nkw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestBTest {

    private TestB testB;

    @BeforeEach
    public void init() {
        testB = new TestB();
    }

    @Test
    public void test_main_B() {
        assertThat(4).isEqualTo(4);
    }

}