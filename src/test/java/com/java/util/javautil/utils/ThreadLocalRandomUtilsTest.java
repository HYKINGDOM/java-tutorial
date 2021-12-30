package com.java.util.javautil.utils;

import org.junit.Test;

import static com.java.util.javautil.utils.ThreadLocalRandomUtils.generateRandomInteger;
import static org.junit.Assert.*;

public class ThreadLocalRandomUtilsTest {

    @Test
    public void test_generate_Random_Integer() {
        Integer randomInteger = generateRandomInteger();
        System.out.println(randomInteger);
        assertNotNull(randomInteger);

    }
}