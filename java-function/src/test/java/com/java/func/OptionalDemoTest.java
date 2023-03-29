package com.java.func;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalDemoTest {


    @Test
    public void test_Optional_Demo_Test_01() {
        Integer value1 = null;
        Integer value2 = 1;
        try {
            Optional<Integer> optional1 = Optional.empty();
            System.out.println("optional1创建了");
        } catch (Exception e) {
            System.out.println("optional1失败了");
        }
        try {
            Optional<Integer> optional2 = Optional.of(value1);
            System.out.println("optional2创建了");
        } catch (Exception e) {
            System.out.println("optional2失败了");
        }
        try {
            Optional<Integer> optional3 = Optional.ofNullable(value1);
            System.out.println("optional3创建了");
        } catch (Exception e) {
            System.out.println("optional3失败了");
        }
        try {
            Optional<Integer> optional4 = Optional.of(value2);
            System.out.println("optional4创建了");
        } catch (Exception e) {
            System.out.println("optional4失败了");
        }
        try {
            Optional<Integer> optional5 = Optional.ofNullable(value2);
            System.out.println("optional5创建了");
        } catch (Exception e) {
            System.out.println("optional5失败了");
        }
    }


    @Test
    public void test_Optional_Demo_Test_02() {
        Integer value1 = null;
        Integer value2 = 1;
        Optional<Integer> optional1 = Optional.ofNullable(value1);
        Optional<Integer> optional2 = Optional.of(value2);
        try {
            Integer result = optional1.get();
            System.out.println("optional1的值是：" + result);
        } catch (Exception e) {
            System.out.println("optional1的值获取失败,原因:" + e.getMessage());
        }
        try {
            Integer result = optional2.get();
            System.out.println("optional2的值是：" + result);
        } catch (Exception e) {
            System.out.println("optional2的值获取失败,原因:" + e.getMessage());
        }
    }


    @Test
    public void test_Optional_Demo_Test_03() {

        Integer value1 = null;
        Integer value2 = 1;
        Optional<Integer> optional1 = Optional.ofNullable(value1);
        Optional<Integer> optional2 = Optional.of(value2);
        try {
            if (optional1.isPresent()) {
                System.out.println("optional1的isPresent结果不为空");
            } else {
                System.out.println("optional1的isPresent结果为空");
            }
        } catch (Exception e) {
            System.out.println("optional1的isPresent判空失败,原因:" + e.getMessage());
        }
        try {
            if (optional2.isPresent()) {
                System.out.println("optional2的isPresent结果不为空");
            } else {
                System.out.println("optional2的isPresent结果为空");
            }
        } catch (Exception e) {
            System.out.println("optional2的isPresent判空失败,原因:" + e.getMessage());
        }


        optional1.ifPresent(t -> {
            int i = t + 1;
            System.out.println("optional1处理后的值是" + i);
        });
        optional2.ifPresent(t -> {
            int i = t + 1;
            System.out.println("optional2处理后的值是" + i);
        });


        Integer value3 = 2;
        Integer result = optional1.orElse(value3);
        System.out.println("optional1执行orElse处理后的值是" + result);


        result = optional2.orElse(value3);
        System.out.println("optional2执行orElse处理后的值是" + result);


        result = optional1.orElseGet(() -> -1);
        System.out.println("optional1执行orElseGet处理后的值是" + result);


        result = optional2.orElseGet(() -> -1);
        System.out.println("optional2执行orElseGet处理后的值是" + result);
        try {
            result = optional1.orElseThrow(() -> new RuntimeException("值是空的"));
            System.out.println("optional1执行orElseThrow处理后的值是" + result);
        } catch (Exception e) {
            System.out.println("optional1的orElseThrow抛出异常:" + e.getMessage());
        }
        try {
            result = optional2.orElseThrow(() -> new RuntimeException("值是空的"));
            System.out.println("optional2执行orElseThrow处理后的值是" + result);
        } catch (Exception e) {
            System.out.println("optional2的orElseThrow抛出异常:" + e.getMessage());
        }

    }


    @Test
    public void test_Optional_Demo_Test_04() {

        Integer value1 = 5;
        Integer value2 = 6;
        Optional<Integer> optional1 = Optional.ofNullable(value1);
        Optional<Integer> optional2 = Optional.of(value2);


        Optional<Integer> result = optional1.filter(t -> t > 5);
        System.out.println("optional1的filter后的值:" + result);
        result = optional2.filter(t -> t > 5);
        System.out.println("optional2的filter后的值:" + result);
    }


    @Test
    public void test_Optional_Demo_Test_05() {
        User user1 = null;
        User user2 = new User("user2名字", 19);
        Optional<User> optional1 = Optional.ofNullable(user1);
        Optional<User> optional2 = Optional.of(user2);
        System.out.println("=========map==========");
        System.out.println("optional1的map前的值:" + optional1);
        Optional<String> result = optional1.map(User::getName);
        System.out.println("optional1的map后的值:" + result);


        System.out.println("optional2的map前的值:" + optional2);
        result = optional2.map(User::getName);
        System.out.println("optional2的map后的值:" + result);


        System.out.println("===========flatMap========");


        System.out.println("optional1的flatMap前的值:" + optional1);
        Optional<Integer> result2 = optional1.flatMap(t -> Optional.ofNullable(t.getAge()));
        System.out.println("optional1的flatMap后的值:" + result2);


        System.out.println("optional2的flatMap前的值:" + optional2);
        result2 = optional2.flatMap(t -> Optional.ofNullable(t.getAge()));
        System.out.println("optional2的flatMap后的值:" + result2);
    }


    @Test
    public void test_Optional_Demo_Test_06() {
    }


    @Test
    public void test_Optional_Demo_Test_07() {
    }


    public class User {
        String name;
        Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }


        public String getName() {
            return name;
        }


        public Integer getAge() {
            return age;
        }
    }
}
