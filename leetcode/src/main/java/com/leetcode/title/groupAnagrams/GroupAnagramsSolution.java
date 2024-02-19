package com.leetcode.title.groupAnagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GroupAnagramsSolution {

    public List<List<String>> groupAnagrams(String[] strs) {

        List<Integer> flagList = new ArrayList<>();
        List<List<String>> listArrayList = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            List<String> strList = new ArrayList<>();
            if (flagList.contains(i)) {
                continue;
            }
            String strI = strs[i];
            strList.add(strI);
            flagList.add(i);
            for (int j = i + 1; j < strs.length; j++) {
                if (!flagList.contains(j)) {
                    String strJ = strs[j];
                    if (strI.length() == strJ.length()) {
                        List<String> stringList1 = Arrays.asList(strI.split(""));
                        stringList1.sort(null);
                        String strI1 = stringList1.toString();

                        List<String> stringList2 = Arrays.asList(strJ.split(""));
                        stringList2.sort(null);
                        String strJ1 = stringList2.toString();

                        if (strI1.equals(strJ1)) {
                            flagList.add(j);
                            strList.add(strJ);
                        }
                    }
                }
            }
            listArrayList.add(strList);
        }

        Collections.reverse(listArrayList);
        return listArrayList;
    }

}
