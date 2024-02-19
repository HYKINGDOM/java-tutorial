package com.leetcode.hw.stringmerge;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StringMerge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String next = scanner.next();

            String trimStr = next.trim();

            Map<Integer, String> objectHashMap = getIntegerStringMap(trimStr);

            //翻转
            String newNum = getNewNum(objectHashMap);
            System.out.print(newNum);
        }
    }

    private static String getNewNum(Map<Integer, String> objectHashMap) {
        String newNum = "";
        List<String> stringList = new ArrayList<>(objectHashMap.values());
        for (String item : stringList) {
            //转换为二进制
            int asc = (int)item.charAt(0);
            if ((asc >= 48 && asc <= 57) || (asc >= 97 && asc <= 102) || (asc >= 65 && asc <= 70)) {
                String num = Integer.toBinaryString(Integer.valueOf(item, 16));
                //在前面补0
                if (num.length() < 4) {
                    DecimalFormat d = new DecimalFormat("0000");
                    num = d.format(Integer.valueOf(num));
                }
                String reverse = new StringBuffer(num).reverse().toString();
                newNum = Integer.toHexString(Integer.valueOf(reverse, 2));
                if (Character.isLetter(newNum.charAt(0))) {
                    newNum = newNum.toUpperCase();
                }
            } else {
                newNum = item;
            }
        }
        return newNum;
    }

    public static Map<Integer, String> getIntegerStringMap(String trimStr) {
        Map<Integer, String> objectHashMap = new HashMap<>();
        char[] chars = trimStr.replaceAll(" ", "").trim().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            objectHashMap.put(i, String.valueOf(chars[i]));
        }

        List<String> oddNumber = new ArrayList<>();
        List<String> evenNumber = new ArrayList<>();

        for (Integer integer : objectHashMap.keySet()) {

            String str = objectHashMap.get(integer);
            if (integer % 2 == 0) {
                evenNumber.add(str);
            } else {
                oddNumber.add(str);
            }
        }

        List<String> oddCollect = oddNumber.stream().sorted().collect(Collectors.toList());
        List<String> evenCollect = evenNumber.stream().sorted().collect(Collectors.toList());

        int size = objectHashMap.keySet().size();

        for (int i = 0; i < size; i++) {
            if (i % 2 == 0 && !evenCollect.isEmpty()) {
                String evenStr = evenCollect.get(0);
                objectHashMap.put(i, evenStr);
                evenCollect.remove(0);
            } else if (!oddCollect.isEmpty()) {
                String oddStr = oddCollect.get(0);
                objectHashMap.put(i, oddStr);
                oddCollect.remove(0);
            }
        }

        return objectHashMap;
    }
}
