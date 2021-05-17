package com.java.util.javautil.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

public class QrCodeUtilDemo {

    public static void main(String[] args) {
        String decode = QrCodeUtil.decode(FileUtil.file("F:/NewProject/java-util/src/main/resources/qrcode/20200409145233-691904.png"));
        System.out.println(decode);
    }
}
