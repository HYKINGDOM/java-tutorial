package com.java.coco.demo;

import com.google.common.collect.Sets;

import java.util.HashSet;

public class JavaSet {

    public static void main(String[] args) {
        HashSet<Object> objectHashSet01 = new HashSet<>();
        HashSet<Object> objectHashSet02 = new HashSet<>();


        System.out.println(!Sets.difference(objectHashSet01,objectHashSet02).isEmpty());
    }
}
