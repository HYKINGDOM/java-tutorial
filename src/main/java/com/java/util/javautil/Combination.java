package com.java.util.javautil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Combination {

    private final Stack<String> stackStr = new Stack<>();

    public List<String> stringList = new ArrayList<>();

    /**
     * 只输出不重复的组合
     *
     * @param shu 待组合数组
     * @param tag 数组长度
     * @param cur 基数(起始位置, 默认0)
     */
    public void funLoopUniqueStr(String[] shu, int tag, int cur) {
        if (cur == tag) {
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
            funLoopUniqueStr(shu, tag, cur + 1);
            stackStr.pop();
        }
    }

    /**
     * 输出所有组合包括重复组合
     *
     * @param shu 待组合数组
     * @param tag 数组长度
     * @param cur 基数(起始位置, 默认0)
     */
    public void funLoopRepeatedStr(String[] shu, int tag, int cur) {
        if (cur == tag) {
            stringList.add(stackStr.toString());
            //System.out.println(stackStr);
            return;
        }
        for (String j : shu) {
            stackStr.add(j);
            funLoopRepeatedStr(shu, tag, cur + 1);
            stackStr.pop();
        }
    }
}
