package com.leetcode.hw.nkw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[][] strings = new String[][]{};
        int num = 4;
        while (in.hasNext()) {
            String next = in.nextLine();

            String[] str = next.split(" ");

            if (str.length == 2) {
                strings = new String[Integer.parseInt(str[0])][Integer.parseInt(str[1])];
            } else {
                int length = strings.length;
                for (int i = 0; i < str.length; i++) {
                    for (int j = 0; j < strings.length; j++) {
                        if (strings[j].length < length) {
                            strings[j][i] = str[i];
                        }
                    }
                }
            }
            if (num == 0) {
                System.out.println(2);
            }
            num--;
        }
    }

}


//    Scanner in = new Scanner(System.in);
//    int k = 0;
//    int num = 0;
//    String space = " ";
//    List<String> resultList = new ArrayList<>();
//        while (in.hasNext()) {
//                String next = in.nextLine();
//                next = next.replaceAll("\\(", space);
//                next = next.replaceAll("\\)", space);
//                String[] split = next.split(space);
//                for (String str : split) {
//                if (str.contains(",") && !str.endsWith(",")) {
//                String[] split1 = str.split(",");
//                String s0 = split1[0];
//                String s1 = split1[1];
//                resultList.add(str);
//
//                if (!s0.startsWith("0") && !s1.startsWith("0")) {
//                int i0 = Integer.parseInt(s0);
//                int i1 = Integer.parseInt(s1);
//                if (i1 != 0 && i0 != 0) {
//                int n = i0 * i0 + i1 * i1;
//                if (k == 0) {
//                k = n;
//                num = resultList.size();
//                } else if (n > k) {
//                k = n;
//                num = resultList.size();
//                }
//                }
//                }
//                }
//                }
//                if (!resultList.isEmpty()) {
//                if (k != 0) {
//                String s = "(" + resultList.get(num == 0 ? 0 : num - 1) + ")";
//                System.out.println(s);
//                } else {
//                System.out.println("(0,0)");
//                }
//                } else {
//                System.out.println("(0,0)");
//                }
//                resultList.clear();
//                k = 0;
//                num = 0;
//                }

//ferg(3,10)a13fdsf3(3,4)f2r3rfasf(05,10)
//ferg(3,10)a13fdsf3(3,4)f2r3rfasf(5,10)
// 如：ferga13fdsf3(100,200)f2r3rfasf(300,400)
//     if (array1 != null && array2 != null && k != 0) {
//        int maxFlag = Math.max(array1.length, array2.length);
//        int minFlag = Math.min(array1.length, array2.length);
//
//        for (int j = 1; j < minFlag; j++) {
//            for (int i = 0; i < k; i++) {
//                if (j + i < minFlag) {
//                    sum = sum + (Integer.parseInt(array1[j + i]) + Integer.parseInt(array2[j + i]));
//                }
//            }
//            resultList.add(sum);
//            sum = 0;
//        }
//        Integer integer = resultList.stream().sorted().collect(Collectors.toList()).get(0);
//        System.out.println(integer);
//    }
