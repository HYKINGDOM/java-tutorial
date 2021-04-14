package com.java.util.javautil.system.leecode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class RepeatString {


    @Test
    public void test_repeat_string() {

        int i = lengthOfLongestSubstring("pwwkew");
        Assert.assertEquals(1, i);
    }

    /**
     * 字符串中最长的不重复字符串长度
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Set<String> treeList = new TreeSet<>();
        for (int j = 0; j < length; j++) {
            treeList.add(s.substring(j, j + 1));
        }
        return treeList.size();
    }


    /***************** UAV ****************/

    @Test
    public void test_uav_start() {

    }

    @Test
    public void check_uvaName_and_coordinate_is_number_or_string_or_int() {

        Assert.assertTrue("UAV is breakdown status", uavException("plant..456", -1, -3, 4));

    }

    /**
     * 校验UAV和坐标
     *
     * @param uavName
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean uavException(String uavName, int x, int y, int z) {
        boolean strflag = true;

        int uavNameLength = uavName.length();
        for (int i = 0; i < uavNameLength; i++) {
            String str = uavName.substring(i, i + 1);
            if (!checkIsNumber(str) && !checkIsString(str)) {
                strflag = false;
                break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(x);
        stringBuilder.append(y);
        stringBuilder.append(z);
        if (!strflag || stringBuilder.toString().contains(".")) {
            strflag = false;
        }

        return strflag;
    }

    /**
     * 检查该字符串是否是数字类型
     *
     * @param str
     * @return
     */
    public boolean checkIsNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 检查字符串是否是字母
     *
     * @param str
     * @return
     */
    public boolean checkIsString(String str) {
        char c = str.charAt(0);
        return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
    }


    @Test
    public void test2() {
        int a = 2;
        int b = 3;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a + ",b=" + b);
    }

    @Test
    public void check_x_or() {
        int[] arr = new int[]{5, 4, 8, 9, 5, 1, 2, 3, 5, 4, 8, 10};
        System.out.println(Arrays.toString(arr));
        int length = arr.length;
        int half = length / 2;
        for (int i = 0; i < half; i++) {
            arr[i] = arr[length - 1 - i] ^ arr[i];
            arr[length - 1 - i] = arr[length - 1 - i] ^ arr[i];
            arr[i] = arr[length - 1 - i] ^ arr[i];
        }

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test_check_x() {
        int[] arr = {8, 2, 3, 4, 5, 2, 3, 4, 5, 9};
        int i, v = 0;
        for (i = 0; i < arr.length; i++) {
            v ^= arr[i];
        }
        System.out.println(v);
    }

}
