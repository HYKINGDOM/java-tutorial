package com.leetcode.title.mobilenumber;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MobileNumberTest {

    private MobileNumber mobileNumber;


    @BeforeEach
    public void init() {
        mobileNumber = new MobileNumber();
    }


    @Test
    public void test_main_A() {
        List<String> result = mobileNumber.letterCombinations("");
        assertThat(result).isEmpty();
    }


    @Test
    public void test_main_A_1() {
        List<String> result = mobileNumber.letterCombinations("2");
        assertThat(result).isEqualTo(Lists.newArrayList("a", "b", "c"));
    }
}