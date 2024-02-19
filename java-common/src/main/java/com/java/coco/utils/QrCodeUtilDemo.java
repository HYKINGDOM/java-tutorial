package com.java.coco.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

public class QrCodeUtilDemo {

    /**
     * https://adl.netease.com/d/g/yys/c/gw?from=qr
     *
     * @param args 入参
     */
    public static void main(String[] args) {
        String decode = QrCodeUtil.decode(FileUtil.file("H:/57b280b496dee47507111c56NRN73rVj.png"));
        System.out.println(decode);
    }
}
