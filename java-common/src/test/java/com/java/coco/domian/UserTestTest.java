package com.java.coco.domian;

import cn.hutool.core.bean.BeanUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTestTest {


    @Test
    public void test_user_01(){
        UserTestA userTestA = new UserTestA();
        userTestA.setId(1);
        userTestA.setUsername("AAA");
        userTestA.setPassword("2222");

        UserTestB userTestB = new UserTestB();
        BeanUtil.copyProperties(userTestA,userTestB);

        System.out.println(userTestB.toString());
    }


}