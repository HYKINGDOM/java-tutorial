package com.java.util.javautil;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class DemoTest {

    public static void main(String[] args) {
        DemoTest demoTest = new DemoTest();
        demoTest.optionalMethod();
    }

    public String optionalMethod() {

        List<TestVo> voList = new ArrayList<>();
        java.util.Date date = new java.util.Date();
        TestVo testVo01 = TestVo.builder().dateTimeVo(date).build();
        TestVo testVo02 = TestVo.builder().dateTimeVo(date).build();
        TestVo testVo03 = TestVo.builder().dateTimeVo(date).build();
        voList.add(testVo01);
        voList.add(testVo02);
        voList.add(testVo03);

        List<TestDO> doList = new ArrayList<>();
        voList.forEach(e -> doList.add(convertToDo(e)));
        doList.forEach(System.out::println);
        return null;
    }

    private TestDO convertToDo(TestVo testVo) {
        return TestDO.builder().dateTimeDO(new java.sql.Date(testVo.getDateTimeVo().getTime())).build();
    }

    @Data
    @Setter
    @Getter
    @ToString
    @Builder
    public static class TestVo {
        private java.util.Date dateTimeVo;
    }

    @Setter
    @Getter
    @ToString
    @Builder
    public static class TestDO {
        private java.sql.Date dateTimeDO;
    }
}
