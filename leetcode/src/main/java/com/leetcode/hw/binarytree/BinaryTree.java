package com.leetcode.hw.binarytree;

import java.util.Scanner;

public class BinaryTree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int n = Integer.parseInt(line);
        System.out.println(numTrees(n));


    }

    public static int numTrees(int n) {
        int[] dp = new int[n + 1];//0个节点的子树也是一种情况，所以有n+1个
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
