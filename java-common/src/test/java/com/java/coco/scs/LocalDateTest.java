package com.java.coco.scs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class LocalDateTest {

    @Test
    public void test_local(){
        LocalDate now = LocalDate.now();
        System.out.println(now.minusDays(1));
    }
}
