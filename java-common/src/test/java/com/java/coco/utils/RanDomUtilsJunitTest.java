package com.java.coco.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RanDomUtilsJunitTest {


    private RanDomUtils ranDomUtils = new RanDomUtils();
    private String expUserAccount = ranDomUtils.randomAlphanumeric(15);
    private String expUserName = ranDomUtils.randomAlphanumeric(10);
    private String expUserEmail = ranDomUtils.randomNumeric(8) + "@qq.com";
    private String expUserPassword = ranDomUtils.randomGraph(20);

//    private String expUserAccount;
//    private String expUserName;
//    private String expUserEmail;
//    private String expUserPassword;
//
//    private RanDomUtils ranDomUtils;
//
//    @BeforeEach
//    public void init_class() {
//        ranDomUtils = new RanDomUtils();
//        expUserAccount = ranDomUtils.randomAlphanumeric(15);
//        expUserName = ranDomUtils.randomAlphanumeric(10);
//        expUserEmail = ranDomUtils.randomNumeric(8) + "@qq.com";
//        expUserPassword = ranDomUtils.randomGraph(20);
//    }


    @Test
    public void test_case_01() {
        System.out.println(expUserAccount);
        System.out.println(expUserEmail);
        System.out.println(expUserName);
        System.out.println(expUserPassword);
        System.out.println("================test01===============");
    }


    @Test
    public void test_case_02() {
        System.out.println(expUserAccount);
        System.out.println(expUserEmail);
        System.out.println(expUserName);
        System.out.println(expUserPassword);
        System.out.println("================test02===============");
    }


}