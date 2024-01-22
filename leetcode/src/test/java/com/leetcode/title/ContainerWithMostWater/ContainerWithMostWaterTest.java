package com.leetcode.title.ContainerWithMostWater;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.lang.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ContainerWithMostWaterTest {

    private ContainerWithMostWater containerWithMostWater;

    @BeforeEach
    void setUp() {

        containerWithMostWater = new ContainerWithMostWater();
    }

    @Test
    public void test_demo_001() {
        int[] intArray = new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = containerWithMostWater.maxArea(intArray);
        Assertions.assertEquals(49, maxArea);
    }

    @Test
    public void test_demo_002() {
        int[] intArray = new int[] {1, 1};
        int maxArea = containerWithMostWater.maxArea(intArray);
        Assertions.assertEquals(1, maxArea);
    }

    @Test
    public void test_demo_003() throws IOException {
        Resource stateFile = new ClassPathResource("/data/ContainerWithMostWater/demo03.txt");

        List<String> strings = FileUtil.readLines(stateFile.getUrl(), StandardCharsets.UTF_8);

        int[] intArray1 = Convert.convert(new TypeReference<int[]>() {
        }, strings.toArray());

        int maxArea = containerWithMostWater.maxArea(intArray1);
        Assertions.assertEquals(22384768, maxArea);
    }

}