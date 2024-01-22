package com.leetcode.title.zigzagConversion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class zigzagConversionTest {

    private zigzagConversion zigzagConversion;

    @BeforeEach
    public void init_test() {
        zigzagConversion = new zigzagConversion();
    }

    @Test
    public void testZigzag_only_single() {
        String a = zigzagConversion.convert("A", 1);
        assertEquals(a, "A");
    }

    @Test
    public void testZigzag_first() {
        String str = "PAYPALISHIRING";
        String a = zigzagConversion.convert(str, 3);
        assertEquals(a, "PAHNAPLSIIGYIR");
    }

    @Test
    public void testZigzag_second() {
        System.out.println(2 % 4);
    }

}