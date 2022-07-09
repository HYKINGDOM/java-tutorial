package com.java.coco.utils;

import org.junit.Test;

import static com.java.coco.utils.ThreadLocalRandomUtils.generateRandomInteger;
import static org.junit.Assert.assertNotNull;

public class ThreadLocalRandomUtilsTest {

    @Test
    public void test_generate_Random_Integer() {
        Integer randomInteger = generateRandomInteger();
        System.out.println(randomInteger);
        assertNotNull(randomInteger);

    }
}