package com.java.coco.utils.zip;

import com.google.common.base.Splitter;
import net.lingala.zip4j.exception.ZipException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.java.coco.utils.zip.TestUnzipDirWithPassword.unzip;

public class PasswordZipGuess {

    private static final String PATH_ZIP = "E:\\download\\demo-cloud-demo.zip";
    public static Stack<String> stackStr = new Stack<>();
    public static List<String> stringList = new ArrayList<>();
    public static String password;

    public static void main(String[] args) throws ZipException {
        //String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-_+=~`[]{}|\\\\:;\\\"'<>,.?/";
        //String str = "0123456789abcdefghijklmnopqrstuvwxyz";
        String str = "1234567890";
        List<String> stringList = Splitter.fixedLength(1).splitToList(str);
        String[] strArray = new String[stringList.size()];
        stringList.toArray(strArray);
        password = null;
        PasswordZipGuess passwordZipGuess = new PasswordZipGuess();
        long timeMillis01 = System.currentTimeMillis();
        passwordZipGuess.funLoopStr(strArray, 4, 0);
        long timeMillis02 = System.currentTimeMillis();
        System.out.println("找到密码!!!,密码为" + password + ", 总共耗时: " + (timeMillis02 - timeMillis01));
    }

    public void funLoopStr(String[] shu, int targ, int cur) throws ZipException {
        if (cur == targ) {
            System.out.println(stackStr);
            String toString = stackStr.toString();
            toString = toString.replace(",", "");
            toString = toString.replace("[", "");
            toString = toString.replace("]", "");
            toString = toString.replace(" ", "");

            try {
                unzip(PATH_ZIP, toString);
                password = toString;
                System.out.println("找到密码!!!");
                return;
            } catch (ZipException e) {
                //System.out.println("该密码无效！" + e.getMessage());
                DeleteFileUtil.deleteDirectory(PATH_ZIP.split("\\.")[0]);
            }
            return;
        }

        for (String j : shu) {
            stackStr.add(j);
            funLoopStr(shu, targ, cur + 1);
            stackStr.pop();
            if (password != null) {
                break;
            }
        }
    }
}
