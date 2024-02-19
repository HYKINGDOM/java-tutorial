package com.leetcode.title.ContainerWithMostWater;

/**
 * @author meta
 */
public class ContainerWithMostWaterMe {

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
        for (int i = 0; i < length; i++) {

            int i1 = height[i];

            for (int j = i + 1; j < length; j++) {

                int j1 = height[j];

                if (i1 > j1) {

                    max = getMax(j, i, j1, max);

                } else if (i1 < j1) {

                    max = getMax(j, i, i1, max);
                } else {

                    max = getMax(j, i, i1, max);
                }

            }

        }

        return max;
    }
}
