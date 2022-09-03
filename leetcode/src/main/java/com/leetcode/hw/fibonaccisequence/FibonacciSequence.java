package com.leetcode.hw.fibonaccisequence;

import java.util.Scanner;

public class FibonacciSequence {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] dp = new int[num + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= num; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[num]);
    }

}
