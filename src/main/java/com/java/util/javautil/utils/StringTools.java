package com.java.util.javautil.utils;

import cn.hutool.core.util.StrUtil;

public class StringTools {


    public static void main(String[] args) {
        String userAccount = "我会告诉你  这是我最引以为豪的方法吗";

        System.out.println(StrUtil.length(userAccount));
        System.out.println(userAccount.length());
        System.out.println(userAccount.trim().length());

    }
}
