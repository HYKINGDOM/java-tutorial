package com.java.coco.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListAddAllHashSet {

    public static void main(String[] args) {
        Set<String> stringHashSet = new HashSet<>();
        List<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("001");
        stringArrayList.add("002");
        stringArrayList.add("004");
        stringArrayList.add("003");

        for (String s : stringArrayList) {
            System.out.println(s);
        }

        System.out.println("========================");
        stringHashSet.addAll(stringArrayList);
        for (String s : stringHashSet) {
            System.out.println(s);
        }
    }
}
