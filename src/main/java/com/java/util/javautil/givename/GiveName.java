package com.java.util.javautil.givename;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class GiveName {

    public static Stack<String> stackStr = new Stack<>();

    public static List<String> stringList = new ArrayList<>();

    public static void main(String[] args) {
        String str1 = "民文化丰人达通隆鑫兴弘鸿伟";
        List<String> stringList = Splitter.fixedLength(1).splitToList(str1);
        String[] strArray = new String[stringList.size()];
        stringList.toArray(strArray);
        GiveName giveName = new GiveName();
        List<String> password = giveName.createPassword(strArray, 4);
        for (String s : password) {
            System.out.println(s);
        }
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
            s = s.replace("[", "");
            s = s.replace("]", "");
            s = s.replace(",", "");
            s = s.replace(" ", "");
            passwordList.add(s);
        }
        return passwordList;
    }

    public void funLoopStr(String[] shu, int targ, int cur) {
        if (cur == targ) {
            stringList.add(stackStr.toString());
            //System.out.println(stackStr);
            return;
        }
        for (String j : shu) {
            if (stackStr != null && !stackStr.toString().contains(j)) {
                stackStr.add(j);
            } else {
                continue;
            }
            funLoopStr(shu, targ, cur + 1);
            stackStr.pop();
        }
    }
}
