package com.java.coco.utils;

public class PatternUtils {

    public static final String EXPORT_SYMBOL = "/[^-|^（|^）|^\\d|^\\[a-zA-Z\\]|^\\[\\u4e00-\\u9fa5\\]]/g";

    public static boolean isOnlyExportSymbol(String str){
        return str.matches(EXPORT_SYMBOL);
    }
}
