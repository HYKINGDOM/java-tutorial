package com.java.tutorial.junit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;


/**
 * 为了允许隔离执行单个的测试方法，JUnit在执行每个测试方法之前会创建每个测试类的新实例。
 * 如果想改变策略，就要用@TestInstance，在类上添加@TestInstance(TestInstance.Lifecycle.PER_CLASS)。
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JunitOrderTest {
    int a = 0;

    @Test
    @Order(1)
    void A() {
        a++;
        System.out.println("A方法：" + a);
        System.out.println("A");
    }

    @Test
    @Order(2)
    void B() {
        a++;
        System.out.println("B方法：" + a);
        System.out.println("B");
    }

    @Test
    @Order(3)
    void C() {
        a++;
        System.out.println("C方法：" + a);
        System.out.println("C");
    }
}
