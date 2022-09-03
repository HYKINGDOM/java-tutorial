package com.leetcode.hw.climbstairs;

import java.util.Scanner;

/**
 * 3
 * 2 5 20
 */
public class ClimbStairs {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int[] cost = new int[in.nextInt()];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = in.nextInt();
        }
        System.out.println(minCostClimbingStairs(cost));
    }

    private static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[cost.length];
    }
}
