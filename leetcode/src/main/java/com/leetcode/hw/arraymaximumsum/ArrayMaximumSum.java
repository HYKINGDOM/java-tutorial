package com.leetcode.hw.arraymaximumsum;

import java.util.Scanner;

public class ArrayMaximumSum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int anInt = in.nextInt();
        int[] ints = new int[anInt];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = in.nextInt();
        }
        int initMethod = initMethod(anInt, ints);
        System.out.println(initMethod);
    }

    public static int initMethod(int anInt, int[] ints) {
        if (anInt == 1) {
            return (ints[0]);
        }

        int second = 0;

        for (int i = 0; i < anInt; i++) {
            int first = ints[i];
            if (ints[i] == 0) {
                continue;
            }

            if (second == 0) {
                second = first;
            } else if (first > second) {
                second = first;
            }
            for (int j = i + 1; j < anInt; j++) {
                int anIntJ = ints[j];
                if (anIntJ == 0) {
                    continue;
                }
                first = first + anIntJ;
                if (second == 0) {
                    second = first;
                } else if (first > second) {
                    second = first;
                }
            }
        }
        return (second);
    }
}
