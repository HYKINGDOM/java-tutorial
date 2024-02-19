package com.leetcode.hw.binarytree;

import java.util.Scanner;

public class BinaryTreeA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(numOfMethods(n));
    }

    //动态规划
    private static int numOfMethods(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];

        //递归
        //         if (n <= 1) return 1;
        //         int res = 0;
        //         for (int i = 1; i <= n; i++) {
        //             res += numOfMethods(i - 1) * numOfMethods(n - i);
        //         }
        //         return res;
    }
}
