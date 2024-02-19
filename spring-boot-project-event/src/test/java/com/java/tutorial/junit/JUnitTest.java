package com.java.tutorial.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class JUnitTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("我是BeforeAll，我最开始执行。");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("我是AfterAll，我最后执行。");
    }

    static Stream<Arguments> stringProvider() {
        return Stream.of(Arguments.arguments(12, "李四"), Arguments.arguments(18, "王五"),
            Arguments.arguments(20, "小红"));
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("我是BeforeEach，我在每个 Test 前执行。");
    }

    @AfterEach
    void afterEach() {
        System.out.println("我是AfterEach，我在每个 Test 后执行。");
    }

    @Test
    void test0() {
        System.out.println("测试用例1");
    }

    @Test
    void test1() {
        System.out.println("测试用例2");
    }

    @Test
    void test2() {
        System.out.println("测试用例3");
    }

    @ParameterizedTest
    @ValueSource(strings = {"小明", "小红", "小兰"})
    void paramTest(String name) {
        System.out.println(name);
    }

    @ParameterizedTest
    @ValueSource(doubles = {5.2, 2.9, 3.1})
    void paramTestDouble(double num) {
        System.out.println(num);
    }

    @ParameterizedTest
    @CsvSource({"小明, 1", "小红,2", "小兰,3"})
    void csvSource(String name, int id) {
        System.out.println(name + ":" + id);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv")
    void csvFile(String name, int id) {
        System.out.println(name + ": " + id);
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
        //指定方法
    void methodSource(int age, String name) {
        System.out.println(age + ": " + name);
    }

}

