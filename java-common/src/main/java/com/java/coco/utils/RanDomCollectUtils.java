package com.java.coco.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * 生成随机数据集合，用于方法测试
 *
 * @author HY
 */
public class RanDomCollectUtils {


    /**
     * 随机数据集
     *
     * @param strLength 字符长度
     * @param listSize      集合大小
     * @return
     */
    public static List<String> randomCollectList(int strLength, int listSize) {

        if (strLength == 0 || listSize == 0) {
            return null;
        }
        List<String> randomStrings = Lists.newArrayList();

        for (int i = 0; i < listSize; i++) {
            randomStrings.add(randomAlphabetic(strLength));
        }
        return randomStrings;
    }


    /**
     * 随机生成Map数据
     * @param strLength 字符长度
     * @param listSize 数组长度
     * @param mapSize map大小
     * @return
     */
    public static Map<String, List<String>> randomCollectMap(int strLength, int listSize, int mapSize) {
        Map<String, List<String>> objectObjectHashMap = Maps.newHashMap();
        for (int i = 0; i < mapSize; i++) {
            objectObjectHashMap.put(randomAlphabetic(strLength), randomCollectList(strLength, listSize));
        }
        return objectObjectHashMap;
    }
}
