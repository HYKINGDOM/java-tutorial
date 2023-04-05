package com.java.coco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Optional;

public class DemoTestTest {

    private DemoTest demoTest;

    @BeforeEach
    public void init_method() {
        demoTest = new DemoTest();
    }


    @Test
    public void test_init_method_01_is_null() {
        long timeMillis = System.currentTimeMillis();

        String str = "ft22VO.getP101Dt().getTime()";

        Date date = new Date(Optional.ofNullable(timeMillis).orElse(timeMillis));
        System.out.println();
    }


    @Test
    public void test_init_method_01_not_null() {
        long l = System.currentTimeMillis();
    }
}