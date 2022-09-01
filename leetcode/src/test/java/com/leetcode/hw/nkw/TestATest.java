package com.leetcode.hw.nkw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestATest {

    private TestA testA;


    @BeforeEach
    public void init(){
        testA = new TestA();
    }


    @Test
    public void test_main_A(){
        assertThat(4).isEqualTo(4);
    }

}