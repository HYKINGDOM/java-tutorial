package com.java.interview.code.hw;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * 根据输入去除重复，返回去重之后的数据 第一行输入随机数的总数N 接下来M行输入相应的随机数
 */
public class RandomNumRemoveDuplicate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeSet<Integer> treeSet = new TreeSet<>();

        String line = scanner.nextLine();
        Scanner in2 = new Scanner(line);
        while (in2.hasNextLine()) {
            boolean b = in2.hasNext();
            boolean hasNextLine = in2.hasNextLine();
            boolean hasNext = in2.hasNext();
            String string = in2.toString();
            int i = in2.nextInt();

            System.out.println(i);
        }
        //        for (int i = 0; i < nextInt; i++)
        //
        //        {
        //            treeSet.add(scanner.nextInt());
        //        }
        //        treeSet.forEach(System.out::println);
    }

}
