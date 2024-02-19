package com.leetcode.title.mobilenumber;

import java.util.ArrayList;
import java.util.List;

public class MobileNumber {

    public List<String> letterCombinations(String digits) {
        List<String> stringList = new ArrayList<>();

        if ("".equals(digits)) {
            return stringList;
        }

        int length = digits.length();

        String[] strings =
            new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z"};
        char[] charArray = digits.toCharArray();
        for (int i = 0; i < length; i++) {
            char c = charArray[0];

        }

        return stringList;
    }
}
