package com.java.util.javautil.huawei;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountFromStringByCharacterTest {


    private CountFromStringByCharacter countFromStringByCharacter;

    @Before
    public void init() {
        countFromStringByCharacter = new CountFromStringByCharacter();
    }

    @Test
    public void test_demo() {
        int count = countFromStringByCharacter.CountFromStringByChar("ABCabc", "A");
        assertEquals(2, count);
    }
}