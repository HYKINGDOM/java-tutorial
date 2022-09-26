package com.leetcode.title.permute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class PermuteUniqueSolution {

    public List<List<Integer>> permuteUnique(int[] nums) {

        int length = nums.length;

        HashSet<List<Integer>> result = new HashSet<>();

        //List<List<Integer>> result = new ArrayList<>();

        Stack<Integer> path = new Stack<>();

        boolean[] flag = new boolean[length];

        int depth = 0;

        addList(result, path, flag, nums, length, depth);

        return new ArrayList<>(result);
    }

    private void addList(HashSet<List<Integer>> result, Stack<Integer> path, boolean[] flag, int[] nums, int startFlag, int deep) {

        if (startFlag == deep) {
            result.add(new ArrayList<>(path));
            return;
        }


        for (int i = 0; i < startFlag; i++) {
            if (!flag[i]) {
                path.push(nums[i]);
                flag[i] = true;

                addList(result, path, flag, nums, startFlag, deep + 1);

                flag[i] = false;
                path.pop();
            }
        }
    }
}
