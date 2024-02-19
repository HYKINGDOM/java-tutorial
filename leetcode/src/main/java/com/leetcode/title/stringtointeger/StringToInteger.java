package com.leetcode.title.stringtointeger;

public class StringToInteger {

    public int myAtoi(String s) {

        char[] chars = s.toCharArray();

        int first = -1;
        int end = -1;
        int num = 0;

        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {
            String valueOf = String.valueOf(chars[i]);

            if (!Character.isDigit(chars[i])) {

                if ((" ").equals(valueOf)) {
                    if (first == -1) {
                        continue;
                    }
                    break;
                }
                num++;
                if (num >= 2) {
                    if (first == -1) {
                        return 0;
                    }
                    break;
                }
                if ("-".equals(valueOf)) {
                    if (first == -1) {
                        flag = true;
                        continue;
                    }
                    break;
                }
                if ("+".equals(valueOf)) {
                    if (first == -1) {
                        continue;
                    }
                    break;
                }
                if (".".equals(valueOf)) {
                    if (first == -1) {
                        break;
                    }
                    end = i - 1;
                    break;
                }
            }

            if (Character.isDigit(chars[i])) {
                if (first == -1) {
                    if ((int)chars[i] == 0) {
                        continue;
                    }
                    first = i;
                    end = i;
                    continue;
                }

                end = i;
            }
        }

        if (first == -1) {
            return 0;
        }

        String substring = s.substring(first, end + 1);

        if (flag) {
            substring = "-" + substring;
        }

        if (substring.length() > 13) {
            if (flag) {
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }

        long parseLong = Long.parseLong(substring);

        if (parseLong < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        if (parseLong > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return Integer.parseInt(substring);
    }
}
