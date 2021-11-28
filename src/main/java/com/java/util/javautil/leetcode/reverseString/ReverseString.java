package com.java.util.javautil.leetcode.reverseString;

import java.util.spi.LocaleNameProvider;

public class ReverseString {

    public int reverse(int x) {

        String value = String.valueOf(x);
        boolean flag = false;
        if (x < 0) {
            flag = true;
            value = value.replace("-", "");
        }

        char[] chars = value.toCharArray();

        StringBuilder stringBuilder = new StringBuilder();
        if (flag) {
            stringBuilder.append("-");
        }

        for (int i = chars.length - 1; i >= 0; i--) {
            stringBuilder.append(chars[i]);
        }


        String string = stringBuilder.toString();
        long parseLong = Long.parseLong(string);

        if (-2147483648 <= parseLong && parseLong <= 2147483647){
            return Integer.parseInt(string);
        }

        return 0;
    }
}
