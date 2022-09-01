package com.leetcode.hw.mingmingrandom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MingMingRandom {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<Integer> stringList = new ArrayList<>();

        Integer flag = null;
        while (scanner.hasNext()) {
            String next = scanner.next();
            if (flag == null) {
                flag = Integer.valueOf(next);
            } else {
                stringList.add(Integer.valueOf(next));
            }

            if (stringList.size() == flag) {
                stringList.stream().distinct().sorted().forEach(System.out::println);
            }
        }
    }
}
