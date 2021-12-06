package com.java.util.javautil;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.*;

public class DemoTestTest {

    private DemoTest demoTest;

    @Before
    public void init_method(){
        demoTest = new DemoTest();
    }


    @Test
    public void test_init_method_01_is_null(){
        long timeMillis = System.currentTimeMillis();

        String str = "ft22VO.getP101Dt().getTime()";

        Date date = new Date(Optional.ofNullable(timeMillis).orElse(timeMillis));
        System.out.println();
    }


    @Test
    public void test_init_method_01_not_null(){
        long l = System.currentTimeMillis();
    }
}