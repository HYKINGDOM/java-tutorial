package com.java.coco.utils;

import com.google.common.base.Optional;
import com.java.coco.guava.Test1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionalGuavaTest {

    @Test
    public void test_demo_01() {
        Test1 test1 = new Test1();

        Optional<Test1> test1Optional = Optional.fromNullable(test1);
        boolean present = test1Optional.isPresent();
        Test1 test11 = test1Optional.get();

        System.out.println(present);

        System.out.println(test1);


    }
}
