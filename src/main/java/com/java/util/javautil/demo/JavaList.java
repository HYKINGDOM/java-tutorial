package com.java.util.javautil.demo;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaList {


    public static void main(String[] args) {
        List<Long> longList = new ArrayList<>();
        longList.add(100L);
        longList.add(400L);
        longList.add(300L);
        longList.add(200L);
        longList.add(600L);
        //longList.add(500L);

        for (List<Long> longs : Lists.partition(longList, 2)) {
            System.out.println(Arrays.toString(longs.toArray()));
            System.out.println("================");
        }
    }
}
