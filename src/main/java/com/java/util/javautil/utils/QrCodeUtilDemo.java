package com.java.util.javautil.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

public class QrCodeUtilDemo {

    public static void main(String[] args) {
        String decode = QrCodeUtil.decode(FileUtil.file("F:/迅雷下载/yys.png"));
        System.out.println(decode);
    }
}
