package com.java.coco.utils;

import org.junit.jupiter.api.Test;

class SysConvertTest {

    @Test
    public void test_demo_01() {
        SysConvert sysConvert = SysConvert.getInstance();
        String result;

        //16进制转20进制
        result = sysConvert.Convert(16, 20, "ABC.DEF", 10);
        System.out.println(result);

        //10进制转16进制
        result = sysConvert.Convert(10, 16, "1111.9999", 10);
        System.out.println(result);
    }

}