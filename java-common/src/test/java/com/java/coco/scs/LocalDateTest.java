package com.java.coco.scs;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocalDateTest {

    @Test
    public void test_local() {
        LocalDate now = LocalDate.now();
        System.out.println(now.minusDays(1));
    }
}
