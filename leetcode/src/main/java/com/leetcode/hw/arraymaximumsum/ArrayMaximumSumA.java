package com.leetcode.hw.arraymaximumsum;

import java.util.Scanner;

public class ArrayMaximumSumA {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int anInt = in.nextInt();
        long[] ints = new long[anInt];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = in.nextLong();
        }
        long initMethod = initMethod(ints);
        System.out.println(initMethod);
    }

    public static long initMethod(long[] ints) {

        if (ints == null || ints.length < 1) {
            return -1;
        }

        long cur = ints[0];
        // 总的最大子数组和
        long res = ints[0];
        for (int i = 1; i < ints.length; i++) {
            // cur = 当前元素 和 当前元素 + 前一位元素下标对应最大子数组和 中的最大值
            cur = Math.max(ints[i], ints[i] + cur);
            // 更新 res
            res = Math.max(res, cur);
        }
        return res;
    }
}
