package com.leetcode.hw.climbstairs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 3
 * 2 5 20
 */
public class ClimbStairsA {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];
        String[] str = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        int a = 0;
        int b = 0;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = Math.min(arr[i - 2] + a, arr[i - 1] + b);
            a = b;
            b = sum;
        }
        writer.write(b + "\n");
        writer.flush();
        writer.close();
        reader.close();
    }
}
