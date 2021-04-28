package com.java.util.javautil.hw;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字母，然后输出输入字符串中该字母的出现次数。不区分大小写。
 * 输入描述:
 * 第一行输入一个由字母和数字以及空格组成的字符串，第二行输入一个字母。
 * <p>
 * 输出描述:
 * 输出输入字符串中含有该字符的个数。
 *
 * @author hy852
 */
public class CountFromStringByCharacter {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String strLength = (scanner.nextLine());
        System.out.println(strLength + "================");
        String strCharacter = (scanner.nextLine());
        System.out.println(strCharacter + "-------------");
        CountFromStringByCharacter countFromStringByCharacter = new CountFromStringByCharacter();
        System.out.println(countFromStringByCharacter.CountFromStringByChar(strLength, strCharacter));
    }


    public int CountFromStringByChar(String strLength, String character) {
        String toLowerCase = character.toLowerCase();
        if (strLength != null && strLength.toLowerCase().contains(toLowerCase)) {
            strLength = strLength.toLowerCase();
            int length = strLength.length();
            int flag = 0;
            for (int i = 0; i < length; i++) {
                String substring = strLength.substring(i, i + 1);
                if (substring.equals(toLowerCase)) {
                    flag++;
                }
            }
            return flag;
        }
        return 0;
    }
}
