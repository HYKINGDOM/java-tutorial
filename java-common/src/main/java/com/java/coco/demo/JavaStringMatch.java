package com.java.coco.demo;

import java.util.ArrayList;
import java.util.List;

public class JavaStringMatch {

    private final static String UNDER_LINE = "_";
    private List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        new JavaStringMatch().go();
    }

    //递归查找字符串中指定字符出现的次数
    public static int searchEleNum(String str, String targetEle) {    //参数为字符串和指定字符

        if (str.indexOf(targetEle) == -1) {
            return 0;
        } else {
            //从当前找到位置的下一个位置下标开始，截取字符串，再进行递归Enum
            return 1 + searchEleNum(str.substring(str.indexOf(targetEle) + 1), targetEle);
        }
    }

    //递归替换，将母字符串的目标字符串，替换成指定字符串
    public static String replaceAll(String parent, String targetEle, String replaceEle) {

        //当目标元素不存在时，返回母字符串
        if (parent.indexOf(targetEle) == -1) {

            return parent;
        } else {                    //目标元素存在时，采用截取的方式进行递归

            //获取目标元素开始下标
            int beginIndex = parent.indexOf(targetEle);
            //获取目标元素结束位置的下一位置下标
            int endIndex = beginIndex + targetEle.length();

            //采用递归的方法，截取目标元素在parent中的前面字符串 + 替换字符串 + 目标元素在parent中的后面字符串递归
            //注意：substring()方法，当有两个参数时，后者所表示下标元素取不到
            return parent.substring(0, beginIndex) + replaceEle + replaceAll(parent.substring(endIndex), targetEle,
                replaceEle);
        }

    }

    //判断email地址是否合法
    public static boolean ifEmeil(String email) {

        //字符串不为空
        if (email != null && !"".equals(email)) {

            //采用正则验证邮箱地址合法性
            if (email.matches(
                "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")) {

                return true;
            } else {

                return false;
            }
        }

        return false;
    }

    private void go() {
        String input = "how many Credits is glob prok Silver how many Credits is glob prok Iron";

        input = input.replaceAll(" ", UNDER_LINE);

        String str = "glob_prok";
        int i = input.indexOf(UNDER_LINE, 1);
        int i2 = input.indexOf(UNDER_LINE, i + 1);
        String substring = input.substring(0, i);

        System.out.println(i);
        System.out.println(i2);
        System.out.println(substring);
        System.out.println(matchStr(input, str, 0, 0));
    }

    private List<String> matchStr(String matchStr, String str, int start, int index) {
        int i = matchStr.indexOf(UNDER_LINE, index);
        String substring = null;
        if (start > 0 && i > start) {
            substring = matchStr.substring(start, i);
        }

        start++;
        matchStr(matchStr, str, start, 1);

        if (str.equals(substring)) {
            list.add(substring);
            matchStr = matchStr.substring(i, matchStr.length());
            matchStr(matchStr, str, start, 1);
        }
        if (matchStr.length() < 3) {
            return list;
        }
        return list;
    }

    /**
     * 把要解析的字符串传进去并进行解析
     */
    private String parseStr(String disStr) {
        String phoneStr = " ";
        if (disStr == null || "null".equals(disStr) || "NULL".equals(disStr)) {
            return phoneStr;
        }
        if (disStr.contains(UNDER_LINE) && disStr.contains(UNDER_LINE)) {
            List<String> resultList = new ArrayList<>();
            resultList = parseAllStr(disStr, 0, 0, resultList);
            phoneStr = getNum(resultList);
        }
        return phoneStr;
    }

    /**
     * 便利解析结果集，并且把第一个符合电话号码的串取出来
     */
    private String getNum(List<String> resultList) {
        for (int i = 0; i < resultList.size(); i++) {
            String regex = "\\d+";
            String currStr = resultList.get(i);
            if (currStr.matches(regex)) {
                return currStr;
            }
        }
        return " ";
    }

    /**
     * 用递归的方法编类所有被【】包围的串
     */
    private List<String> parseAllStr(String disStr, int beginIndex, int endIndex, List<String> resultList) {
        beginIndex = disStr.indexOf(UNDER_LINE, beginIndex);
        endIndex = disStr.indexOf(UNDER_LINE, endIndex);
        if (beginIndex >= 0 && endIndex >= 0) {
            resultList.add(disStr.substring(beginIndex + 1, endIndex));
            parseAllStr(disStr, beginIndex + 1, endIndex + 1, resultList);
        }
        return resultList;
    }
}
