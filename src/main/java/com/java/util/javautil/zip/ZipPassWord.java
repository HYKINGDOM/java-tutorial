package com.java.util.javautil.zip;

import com.google.common.base.Splitter;
import net.lingala.zip4j.exception.ZipException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.java.util.javautil.zip.TestUnzipDirWithPassword.unzip;

public class ZipPassWord {

    public static Stack<String> stackStr = new Stack<>();

    public static List<String> stringList = new ArrayList<>();


    public static void main(String[] args) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-_+=~`[]{}|\\\\:;\\\"'<>,.?/";
        List<String> stringList = Splitter.fixedLength(1).splitToList(str);
        String[] strArray = new String[stringList.size()];
        stringList.toArray(strArray);
        String pathZip = "F:\\test.zip";

        ZipPassWord zipPassWord = new ZipPassWord();
        List<String> passwordList = zipPassWord.createPassword(strArray, 4);

        TxtExport txtExport = new TxtExport();
        txtExport.creatTxtFile("password");
        txtExport.writeTxtFile(passwordList);

//        try {
//            zipPassWord.crackZip(passwordList, pathZip);
//        } catch (ZipException e) {
//            e.printStackTrace();
//        }

    }


    public void crackZip(List<String> stringList, String zipPath) throws ZipException {

        long timeMillis01 = System.currentTimeMillis();
        int num = 0;
        String password = "";
        for (String guessKeyWord : stringList) {
            if (unzip(zipPath, guessKeyWord) != null) {

                password = guessKeyWord;
                break;
            }
            num++;
        }
        long timeMillis02 = System.currentTimeMillis();
        System.out.println("找到密码!!!,密码为" + password + ", 总共耗时: " + (timeMillis02 - timeMillis01));
    }


    /**
     * @param str    密码集合
     * @param length 密码长度
     * @return
     */
    public List<String> createPassword(String[] str, int length) {
        funLoopStr(str, length, 0);
        List<String> passwordList = new ArrayList<>();
        for (String s : stringList) {
            s = s.replace(",", "");
            s = s.replace("[", "");
            s = s.replace("]", "");
            s = s.replace(" ", "");
            passwordList.add(s);
        }
        return passwordList;
    }


    public void funLoopStr(String[] shu, int targ, int cur) {
        if (cur == targ) {
            stringList.add(stackStr.toString());
            System.out.println(stackStr);
            return;
        }

        for (String j : shu) {
            stackStr.add(j);
            funLoopStr(shu, targ, cur + 1);
            stackStr.pop();
        }
    }
}
