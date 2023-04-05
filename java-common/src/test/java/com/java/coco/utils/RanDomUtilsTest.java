package com.java.coco.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RanDomUtilsTest {

    private RanDomUtils ranDomUtils;

    @BeforeEach
    public void init_class() {
        ranDomUtils = new RanDomUtils();
    }

    @Test
    public void test_random_string_and_number_by_length() {
        int length = 100;
        String randomString = ranDomUtils.randomAlphabetic(length);
        System.out.println(randomString);
        assertEquals(length, randomString.length());
    }

    @Test
    public void test_random_string_asc_by_length() {
        int length = 100;
        String randomString = ranDomUtils.randomStringAsci(length);
        System.out.println(randomString);
        assertEquals(length, randomString.length());
    }

    @Test
    public void test_random_string_by_length() {
        int length = 100;
        String randomString = ranDomUtils.randomString(length);
        System.out.println(randomString);
        assertEquals(length, randomString.length());
    }

    @Test
    public void test_random_alphanumeric_by_length() {
        String randomString = ranDomUtils.randomAlphanumeric(100);
        System.out.println(randomString);
        assertEquals(100, randomString.length());
    }

    @Test
    public void test_random_numeric_by_length() {
        String randomString = ranDomUtils.randomNumeric(100);
        System.out.println(randomString);
        assertEquals(100, randomString.length());
    }

    @Test
    public void test_random_graph_by_length() {
        String randomString = ranDomUtils.randomGraph(100);
        System.out.println(randomString);
        assertEquals(100, randomString.length());
    }

    @Test
    public void test_random_print_by_length() {
        String randomString = ranDomUtils.randomPrint(100);
        System.out.println(randomString);
        assertEquals(100, randomString.length());
    }

    @Test
    public void test_random_up_and_down_by_length() {
        String randomString = ranDomUtils.randomByLowString(100);
        System.out.println(randomString);
        assertEquals(100, randomString.length());


        randomString = ranDomUtils.randomByUpString(100);
        System.out.println(randomString);
        assertEquals(100, randomString.length());
    }

    @Test
    public void test_random_input_string_by_length() {
        String randomString = ranDomUtils.randomByInputString(100,"我打算电话卡还是看到hi安徽芜湖嗲话就能看4561wasdawQA");
        System.out.println(randomString);
        assertEquals(100, randomString.length());
    }
}