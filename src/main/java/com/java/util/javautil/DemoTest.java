package com.java.util.javautil;

import java.util.concurrent.ThreadLocalRandom;

public class DemoTest {

    public static void main(String[] args) {


        ThreadLocalRandom current = ThreadLocalRandom.current();
        current.nextDouble();

        for (int i = 0; i < 10; i++) {
            System.out.println(current.nextDouble());
        }
    }

}
