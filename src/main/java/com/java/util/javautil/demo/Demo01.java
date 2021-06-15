package com.java.util.javautil.demo;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo01 {


    public static void main(String[] args) {
        String wwwwsssss = readString("wwwwssssseeeee");



        System.out.println(wwwwsssss);
    }

    public static String readString(String string) {
        String str = "QQ";
        if (string.contains("w")) {
            System.out.println("-----1------");
        } else if (string.contains("s")) {
            System.out.println("-----2------");
        } else if (string.contains("e")) {
            System.out.println("-----3------");
        }
        return str;
    }
}
