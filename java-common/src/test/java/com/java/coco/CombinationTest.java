package com.java.coco;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CombinationTest {

    private Combination combination;

    @Before
    public void beforeInit() {
        combination = new Combination();
    }

    @Test
    public void funLoopUniqueStr() {
        String[] strings = new String[]{"A", "B", "C"};
        combination.funLoopUniqueStr(strings, 2, 0);
        Assert.assertEquals(6, combination.stringList.size());
    }

    @Test
    public void funLoopRepeatedStr() {
        String[] strings = new String[]{"A", "B", "C"};
        combination.funLoopRepeatedStr(strings, 2, 0);
        Assert.assertEquals(9, combination.stringList.size());
    }
}