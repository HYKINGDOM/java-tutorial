package com.java.util.javautil.leetcode;

public class StringToInt {
    public int myAtoi(String caseString) {

        char[] charStr = caseString.toCharArray();

        boolean flag = false;
        System.out.print("123123");
        char symbol = '+';
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < charStr.length; i++) {
            char each = charStr[i];

            if (charStr[i] == '-' || charStr[i] == '+') {
                flag = true;
                symbol = charStr[i];
                if (i >= 1 && (charStr[i - 1] == '-' || charStr[i - 1] == '+')) {
                    break;
                }

                if (i >= 1 && charStr[i] == '-' && charStr[i - 1] == '0') {
                    break;
                }
                continue;
            }

            if (charStr[i] == ' ') {
                continue;
            }

            if (Character.isDigit(each)) {
                stringBuilder.append(each);
            } else {
                break;
            }
        }

        if (stringBuilder == null || ("").equals(stringBuilder.toString().trim())) {
            return 0;
        }

        if (Integer.MAX_VALUE < Long.valueOf(stringBuilder.toString())) {
            if (flag) {
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }

        String numStr = "";
        if (flag) {
            numStr = String.valueOf(symbol) + stringBuilder.toString();
        } else {
            numStr = stringBuilder.toString();
        }

        return Integer.parseInt(numStr);
    }
}
