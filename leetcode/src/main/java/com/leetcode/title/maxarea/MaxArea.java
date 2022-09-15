package com.leetcode.title.maxarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxArea {
    public int maxArea(int[] height) {

        int i = 0;
        int length = height.length - 1;
        int num = 0;
        while (i < length) {
            if (height[i] > height[length]) {
                num = Math.max(Math.abs(length - i) * height[length--], num);
            } else {
                num = Math.max(Math.abs(length - i) * height[i++], num);
            }
        }
        return num;
    }
}
