package com.java.coco.utils;

/**
 * 正则表达式匹配
 */
public class PatternUtil {

    public static final String EXPORT_SYMBOL = "/[^-|^（|^）|^\\d|^\\[a-zA-Z\\]|^\\[\\u4e00-\\u9fa5\\]]/g";
    /**
     * ^[\u4e00-\u9fa5]{0,}$
     *
     * ^[\\u54c8]{0,}$"
     *
     * (?=.*[\\u54c8])
     */
    public static String MOR_CONTAINS_HA = "[\\u54c8]{1,}$";
    private static String ONLY_CONTAINS_NUM_AND_LETTER = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,})$";

    public static boolean isOnlyExportSymbol(String str) {
        return str.matches(EXPORT_SYMBOL);
    }

    public boolean isOnlyCONTAINSNUMANDLETTER(String str) {
        return str.matches(ONLY_CONTAINS_NUM_AND_LETTER);
    }

}
