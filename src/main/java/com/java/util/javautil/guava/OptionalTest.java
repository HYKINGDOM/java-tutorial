package com.java.util.javautil.guava;

import com.google.common.base.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Test1 test1 = new Test1();

        Optional<Test1> test1Optional = Optional.of(test1);
        boolean present = test1Optional.isPresent();
        Test1 test11 = test1Optional.get();

        System.out.println(present);

        System.out.println(test1);


    }
}
