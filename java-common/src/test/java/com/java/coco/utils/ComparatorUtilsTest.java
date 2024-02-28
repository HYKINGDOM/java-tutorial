package com.java.coco.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorUtilsTest {


    @Test
    public void testCustomSort() {
        List<Integer> listA = new ArrayList<>();
        listA.add(1);
        listA.add(2);
        listA.add(3);
        List<Integer> listB = new ArrayList<>();
        listB.add(3);
        listB.add(2);
        listB.add(1);
        ComparatorUtils.customSort(listA, listB);
        assertEquals(listA.get(0), 3);
        assertEquals(listA.get(1), 2);
        assertEquals(listA.get(2), 1);
    }
}