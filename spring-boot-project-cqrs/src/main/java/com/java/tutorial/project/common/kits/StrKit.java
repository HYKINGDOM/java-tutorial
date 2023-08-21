package com.java.tutorial.project.common.kits;

import org.springframework.util.StringUtils;

/**
 *
 * 字符串工具类
 *
 */
public final class StrKit extends StringUtils {

    /**
     * 是否为空
     */
    public static boolean isBlank(CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        }

        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 是否不为空
     */
    public static boolean notBlank(CharSequence str) {
        return !isBlank(str);
    }

}
