package com.leetcode.title.ContainerWithMostWater;

/**
 * @author meta
 */
public class ContainerWithMostWater {

    private static int getMax(int j, int i, int j1, int max) {
        int abs = Math.abs(j - i);

        int i2 = abs * j1;

        if (i2 > max) {
            max = i2;
        }
        return max;
    }

    public int maxArea(int[] height) {

        int length = height.length;
        if (length == 1) {
            return 1;
        }

        int max = 0;
        int left = 0;
        int right = length - 1;

        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            int area = (right - left) * minHeight;
            max = Math.max(max, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
