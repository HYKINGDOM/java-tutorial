package com.java.util.javautil.utils;

/**
 * 正则表达式匹配
 */
public class PatternUtil {

    private static String ONLY_CONTAINS_NUM_AND_LETTER = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,})$";



    public boolean isOnlyCONTAINSNUMANDLETTER(String str){
        return str.matches(ONLY_CONTAINS_NUM_AND_LETTER);
    }
}
