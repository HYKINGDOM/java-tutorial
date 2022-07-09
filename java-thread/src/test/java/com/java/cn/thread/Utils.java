package com.java.util.javautil.scs.thread;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Utils {


    public static SimpleDateFormat sdfymdHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat sdfYMDHms = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    public static DecimalFormat decimalFormat = new DecimalFormat("0");


    /**
     * 去掉字符串右边的空格
     *
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }

}
