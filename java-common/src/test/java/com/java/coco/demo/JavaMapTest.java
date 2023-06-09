package com.java.coco.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.java.coco.utils.RanDomUtils;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Maps;

public class JavaMapTest {

    @Test
    public void mapComputeIfPresent() {

        // 创建一个 HashMap
        HashMap<String, Integer> prices = new HashMap<>();

        // 往HashMap中添加映射关系
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        // 重新计算鞋加上10%的增值税后的价值
        int shoesPrice = prices.computeIfPresent("Shoes", (key, value) -> value + value * 10 / 100);
        System.out.println("Price of Shoes after VAT: " + shoesPrice);

        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);

        Integer integer = prices.computeIfAbsent("Tony", value -> prices.put("Tony", 1000));
        System.out.println("Price of Shoes after VAT: " + integer);
    }

    @Test
    public void mapComputeIfPresent_01() {

        // 创建一个 HashMap
        HashMap<String, Integer> prices = new HashMap<>();

        // 往HashMap中添加映射关系
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        Map<String, List<String>> stringListMap = Maps.newHashMap();

        List<String> strings = Lists.newArrayList();
        strings.add("Shoes");
        strings.add("Bag");
        strings.add("Pant");
        strings.add("Tony");

        for (String string : strings) {
            stringListMap.put(string, Lists.newArrayList());
        }


        List<String> computeIfPresent = stringListMap.computeIfPresent("Shoes", (key, value) -> {
            if (CollectionUtil.isEmpty(value)) {
                value = Lists.newArrayList();
            }

            value.add(RandomUtil.randomString(100));

            return value;
        });

        System.out.println("Price of Shoes after MAP: " + stringListMap);
        System.out.println("Price of Shoes after VAT: " + computeIfPresent);

    }
}