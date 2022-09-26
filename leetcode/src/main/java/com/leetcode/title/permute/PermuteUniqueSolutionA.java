package com.leetcode.title.permute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class PermuteUniqueSolutionA {

    public List<List<Integer>> permuteUnique(int[] nums) {

        int length = nums.length;

        List<List<Integer>> result = new ArrayList<>();

        Stack<Integer> path = new Stack<>();

        boolean[] flag = new boolean[length];

        int depth = 0;

        addList(result, path, flag, nums, length, depth);

        return result;
    }

    private void addList(List<List<Integer>> result, Stack<Integer> path, boolean[] flag, int[] nums, int startFlag, int deep) {

        if (startFlag == deep) {
            result.add(new ArrayList<>(path));
            return;
        }


        for (int i = 0; i < startFlag; i++) {

            if (flag[i] || (i > 0 && nums[i] == nums[i - 1] && !flag[i - 1])) {
                continue;
            }
            path.push(nums[i]);
            flag[i] = true;

            addList(result, path, flag, nums, startFlag, deep + 1);

            flag[i] = false;
            path.pop();
        }
    }
}
