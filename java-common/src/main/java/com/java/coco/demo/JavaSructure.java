package com.java.coco.demo;

import java.util.LinkedList;

public class JavaSructure {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add(".java");
        list.add(".py");
        list.add(".py");
        list.add(".java");
        list.add(".py");
        list.add(".java");
        list.add(".py");
        list.add(".py");
        list.add(".java");
        list.add(".py");
        list.add(".java");
        list.add(".py");
        list.add(".py");
        list.add(".java");
        list.add(".py");
        list.add(".java");
        list.add(".py");
        list.add(".py");
        list.add(".java");
        list.add(".py");
        list.add(".java");
        list.add(".py");
        list.add(".py");
        list.add(".java");
        list.add(".py");
        list.add(".java");
        list.add(".py");
        list.add(".py");
        list.add(".java");
        list.add(".py");

        for (int i = 0; i < list.size(); i++) {
            System.out.println("第 " + i + " 次======");
            String s = list.get(i);
            if (s.contains(".java")) {
                list.remove(i);
                i = i - 1;
            }
            System.out.println("第 " + i + " 次------");
        }

        //list.removeIf(next -> next.endsWith(".java"));

        for (String s : list) {
            System.out.println(s);
        }

    }

}
