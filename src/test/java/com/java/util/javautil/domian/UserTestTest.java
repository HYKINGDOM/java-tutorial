package com.java.util.javautil.domian;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import static org.junit.Assert.*;

public class UserTestTest {


    @Test
    public void test_user_01(){
        UserTestA userTestA = new UserTestA();
        userTestA.setId(1);
        userTestA.setUsername("AAA");
        userTestA.setPassword("2222");

        UserTestB userTestB = new UserTestB();
        BeanUtils.copyProperties(userTestA,userTestB);

        System.out.println(userTestB.toString());
    }


}