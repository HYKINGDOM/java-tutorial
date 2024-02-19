package com.java.interview.code.hw;

import java.util.Scanner;

/**
 * 计算最后一个字符串的长度 字符串使用空格 隔开
 */
public class LastStringLength {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String strLength = (scanner.nextLine());
        System.out.println(strLength + "================");
        LastStringLength lastStringLength = new LastStringLength();
        System.out.println(lastStringLength.calculationLastStringLength(strLength));
    }

    public int calculationLastStringLength(String strLength) {
        if (strLength != null && strLength.contains(" ")) {
            int i = strLength.lastIndexOf(" ");
            String substring = strLength.substring(i + 1);
            return substring.length();
        } else if (strLength != null && !strLength.contains(" ")) {
            return strLength.length();
        }
        return 0;
    }

}
