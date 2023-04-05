package com.java.coco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinationTest {

    private Combination combination;

    @BeforeEach
    public void beforeInit() {
        combination = new Combination();
    }

    @Test
    public void funLoopUniqueStr() {
        String[] strings = new String[]{"A", "B", "C"};
        combination.funLoopUniqueStr(strings, 2, 0);
        assertEquals(6, combination.stringList.size());
    }

    @Test
    public void funLoopRepeatedStr() {
        String[] strings = new String[]{"A", "B", "C"};
        combination.funLoopRepeatedStr(strings, 2, 0);
        assertEquals(9, combination.stringList.size());
    }
}