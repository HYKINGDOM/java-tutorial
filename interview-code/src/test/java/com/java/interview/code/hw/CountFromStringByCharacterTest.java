package com.java.interview.code.hw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountFromStringByCharacterTest {

    private CountFromStringByCharacter countFromStringByCharacter;

    public void init() {
        countFromStringByCharacter = new CountFromStringByCharacter();
    }

    @Test
    public void test_demo() {
        int count = countFromStringByCharacter.CountFromStringByChar("ABCabc", "A");
        assertEquals(2, count);
    }
}