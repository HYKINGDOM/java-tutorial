package com.leetcode.title.groupAnagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GroupAnagramsSolutionA {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> stringListMap = new HashMap<>(strs.length);
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String strSorted = new String(chars);
            List<String> orDefault = stringListMap.getOrDefault(strSorted, new ArrayList<>());
            orDefault.add(str);
            stringListMap.put(strSorted, orDefault);
        }

        return new ArrayList<>(stringListMap.values());
    }


}
