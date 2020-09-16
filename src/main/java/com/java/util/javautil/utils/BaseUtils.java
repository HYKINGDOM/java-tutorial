package com.java.util.javautil.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.MessageDigest;

/**
 * 工具方法类
 *
 * @author Administrator
 */
public class BaseUtils {

    /**
     * 拼接字符串之后翻转字符串
     * @param param
     * @return
     */
    public static String flipStringAfterConcatenation(String param) {
        String filename = RandomStringUtils.randomAlphanumeric(10);
        return reverseString(filename + param);
    }

    /**
     * 翻转字符串之后删减字符串
     * @param param
     * @return
     */
    public static String pruneStringsAfterFlipping(String param) {
        String reverseString = reverseString(param);
        return StringUtils.substring(reverseString, 10);
    }


    /**
     * 倒序字符串
     *
     * @param param
     * @return
     */
    public static String reverseString(String param) {
        if (param == null) {
            return null;
        }
        String strInfo = null;
        try {
            strInfo = new StringBuffer(param).reverse().toString();
        } catch (Exception e) {
            e.getMessage();
        }
        return strInfo;
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String strCreateMD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}
