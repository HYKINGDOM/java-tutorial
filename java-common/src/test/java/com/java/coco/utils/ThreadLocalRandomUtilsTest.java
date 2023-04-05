package com.java.coco.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.java.coco.utils.ThreadLocalRandomUtils.generateRandomInteger;

public class ThreadLocalRandomUtilsTest {

    @Test
    public void test_generate_Random_Integer() {
        Integer randomInteger = generateRandomInteger();
        System.out.println(randomInteger);
        Assertions.assertNotNull(randomInteger);

    }
}