package com.java.coco.scs.javatest;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class TestJavaLocalDateTime {

    @Test
    public void test_true_false() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.plusDays(118);
        LocalDateTime localDateTime1 = now.plusDays(119);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
        System.out.println(localDateTime.getDayOfYear());
        System.out.println(localDateTime1.getDayOfYear());
        long toDays = Duration.between(localDateTime, localDateTime1).toDays();
        System.out.println(toDays);

    }
}
