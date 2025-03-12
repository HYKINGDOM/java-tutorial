package com.java.coco.utils;

import com.google.common.base.Optional;
import com.java.coco.domian.UserTestA;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class OptionalGuavaTest {

    @Test
    public void test_demo_01() {
        UserTestA test1 = new UserTestA();

        Optional<UserTestA> test1Optional = Optional.fromNullable(test1);
        boolean present = test1Optional.isPresent();
        UserTestA test11 = test1Optional.get();

        System.out.println(present);

        System.out.println(test1);

    }

    @Test
    public void test_demo_02() {
        UserTestA test1 = new UserTestA();
        UserTestA test2 = new UserTestA();
        UserTestA or = Optional.fromNullable(test1).or(test2);

        System.out.println(or);
    }

    @Test
    public void test_demo_03() {
        UserTestA test1 = new UserTestA();
        UserTestA test2 = new UserTestA();
        Set<UserTestA> set = Optional.fromNullable(test1).asSet();

        System.out.println(set);
    }
}
