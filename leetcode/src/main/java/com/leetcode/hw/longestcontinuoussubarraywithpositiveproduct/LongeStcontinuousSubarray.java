package com.leetcode.hw.longestcontinuoussubarraywithpositiveproduct;

import java.util.Scanner;

public class LongeStcontinuousSubarray {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int anInt = in.nextInt();
        int[] ints = new int[anInt];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = in.nextInt();
        }
        int initMethod = initLongArrayMethod(anInt, ints);
        System.out.println(initMethod);
    }

    public static int initLongArrayMethod(int anInt, int[] ints) {

        if (ints == null || ints.length < 1) {
            return -1;
        }

        int cur = 1;
        int num = 0;
        int flag = 0;
        for (int i = 0; i < ints.length; i++) {
            cur = ints[i];
            flag = 0;
            for (int j = 1; j < ints.length; j++) {
                cur = cur * ints[j];
                if (cur > 0) {
                    flag++;
                }
            }
            if (flag > num || (cur > 0 && anInt - i > num)) {
                num = Math.max(flag, (anInt - i));
            }
        }

        return num;
    }
}
