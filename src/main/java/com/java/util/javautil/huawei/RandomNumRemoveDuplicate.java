package com.java.util.javautil.huawei;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * 根据输入去除重复，返回去重之后的数据
 * 第一行输入随机数的总数N
 * 接下来M行输入相应的随机数
 */
public class RandomNumRemoveDuplicate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeSet<Integer> treeSet = new TreeSet<>();

        int nextInt = scanner.nextInt();

        for (int i = 0; i < nextInt; i++) {
            treeSet.add(scanner.nextInt());
        }
        treeSet.forEach(System.out::println);
    }


}
