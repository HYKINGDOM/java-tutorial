package com.leetcode.title.combinationSum;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumA {


    private List<List<Integer>> lists = new ArrayList<>();

    private List<Integer> integerLists = new ArrayList<>();


    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        for (int i = 0; i < candidates.length; i++) {
            int candidate = candidates[i];
            integerLists.add(candidate);

        }

        return Lists.newArrayList();
    }



}
