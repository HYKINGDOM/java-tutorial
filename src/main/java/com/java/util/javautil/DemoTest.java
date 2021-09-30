package com.java.util.javautil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DemoTest {

    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("test");
        stringSet.add("test01");
        stringSet.add("test02");


        List<String> result = new ArrayList<>(stringSet);

        for (String s : result) {
            System.out.println(s);
        }
    }
}
