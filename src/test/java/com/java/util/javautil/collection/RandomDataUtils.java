package com.java.util.javautil.collection;

import com.apifan.common.random.source.NumberSource;
import com.apifan.common.random.source.OtherSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomDataUtils {


    public List<Long> generateRandomLongs(int number) {
        long[] longs = NumberSource.getInstance().randomLong(1L, 20000000001L, number);
        return Arrays.stream(longs).boxed().collect(Collectors.toList());
    }

    public List<Integer> generateRandomInts(int number) {
        int[] ints = NumberSource.getInstance().randomInt(0, Integer.MAX_VALUE, number);
        return Arrays.stream(ints).boxed().collect(Collectors.toList());
    }

    public List<String> generateRandomChineseStrings(int number, int length) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            stringList.add(OtherSource.getInstance().randomChinese(length));
        }
        return stringList;
    }
}
