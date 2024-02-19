package com.java.tutorial.project.extensions.java.lang.String;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.apache.commons.lang3.StringUtils;

/**
 * String 的扩展方法 工具类需要使用 Manifold 的 @Extension 注解 静态方法中，目标类型的参数，需要使用 @This 注解 工具类所在的包名，需要以 extensions.目标类型全限定类名 结尾
 */
@Extension
public final class StringExt {

    public static String[] split(@This String str, char separator) {
        return StringUtils.split(str, separator);
    }

    public static String[] splitStr(@This String str, char separator) {
        return StringUtils.split(str, separator);
    }
}
