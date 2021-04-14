package com.java.util.javautil.demo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaMain {
    
    private volatile static String stre = "dasda";

    public static void main(String[] args) {

        String[] strings = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};

        List<String> stringList = Arrays.asList(strings);

        stringList.removeIf(("2")::equals);

        for (String string : strings) {
            System.out.println(string);
        }
    }


    private static void test_for() {

        String[] strings = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};

        List<String> stringList = Arrays.asList(strings);

        List<String> stringArrayList = new ArrayList<>(Arrays.asList(strings));
        stringArrayList.removeIf(("2")::equals);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
