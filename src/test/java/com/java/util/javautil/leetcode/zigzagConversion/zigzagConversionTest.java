package com.java.util.javautil.leetcode.zigzagConversion;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class zigzagConversionTest {


    private zigzagConversion zigzagConversion;


    @Before
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
    public void testZigzag_second(){
        System.out.println(2%4);
    }


}