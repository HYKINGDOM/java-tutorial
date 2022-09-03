package com.leetcode.hw.climbstairs;

import java.util.Scanner;

public class ClimbStairsOne {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int[] cost = new int[in.nextInt()];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = in.nextInt();
        }
        System.out.println(minCostClimbingStairs(cost));
    }

    private static int minCostClimbingStairs(int[] cost) {
        int first = 0;
        int second = 0;
        int third = 0;

        for (int i = 2; i <= cost.length; i++) {
            third = Math.min(first + cost[i - 2], second + cost[i - 1]);
            first = second;
            second = third;
        }
        return third;
    }
}
