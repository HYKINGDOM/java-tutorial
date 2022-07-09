package com.java.coco.utils.zip;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JavaLoopTest {

    private JavaLoop javaLoop;

    @Before
    public void init_java_loop(){
        javaLoop = new JavaLoop();
    }

    @Test
    public void test_factorial(){
        int arrayLength = 3;
        long factorialSum = javaLoop.returnNumFactorial(arrayLength);
        Assert.assertEquals(6,factorialSum);
    }

    @Test
    public void test_create_password_list(){
        int passwordLength = 4;
        String[] str = {"3","4","5","6","7"};
        List<String> passwordList = javaLoop.createPasswordList(str, passwordLength);



    }


}