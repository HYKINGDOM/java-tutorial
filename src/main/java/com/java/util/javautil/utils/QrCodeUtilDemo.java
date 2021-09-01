package com.java.util.javautil.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

public class QrCodeUtilDemo {

    public static void main(String[] args) {
        String decode = QrCodeUtil.decode(FileUtil.file("F:/迅雷下载/57bd6bb096dee4901dea0171sOSFCYQR.png"));
        System.out.println(decode);
    }
}
