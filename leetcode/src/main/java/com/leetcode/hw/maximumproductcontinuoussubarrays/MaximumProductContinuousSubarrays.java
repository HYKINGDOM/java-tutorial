package com.leetcode.hw.maximumproductcontinuoussubarrays;

import java.util.Scanner;

public class MaximumProductContinuousSubarrays {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int anInt = in.nextInt();
        int[] ints = new int[anInt];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = in.nextInt();
        }
        int initMethod = initArrayMethod(anInt, ints);
        System.out.println(initMethod);
    }

    public static int initArrayMethod(int anInt, int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        int n = arr.length;
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];
        dpMax[0] = arr[0];
        dpMin[0] = arr[0];
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            dpMax[i] = Math.max(Math.max(dpMax[i - 1] * arr[i], dpMin[i - 1] * arr[i]), arr[i]);
            dpMin[i] = Math.min(Math.min(dpMax[i - 1] * arr[i], dpMin[i - 1] * arr[i]), arr[i]);
            max = Math.max(dpMax[i], max);
        }
        return max;
    }
}
