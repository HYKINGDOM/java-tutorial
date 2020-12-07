package com.java.util.javautil.zip;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.google.common.math.LongMath.factorial;

public class JavaLoop {


    public long returnNumFactorial(int arrayLength) {
        return factorial(arrayLength);
    }

    public List<String> createPasswordList(String[] str, int passwordLength) {
        long numFactorial = returnNumFactorial(str.length);

        StringBuilder stringBuilder = new StringBuilder();
        List<String> passwordList = new ArrayList<>();

        int nCnt = str.length;

        int nBit = (0xFFFFFFFF >>> (32 - nCnt));

        for (int i = 1; i <= nBit; i++) {
            for (int j = 0; j < nCnt; j++) {
                if ((i << (31 - j)) >> 31 == -1) {
                    System.out.print("输出数组: " + str[j]);
                    stringBuilder.append(str[j]);
                }
            }
            if (stringBuilder.length() == passwordLength) {
                passwordList.add(stringBuilder.toString());
            } else {
                stringBuilder.delete(0, stringBuilder.length());
            }
            System.out.println("");
        }
        for (String s : passwordList) {
            System.out.println(s);
        }

        return null;
    }


    public static Stack<Integer> stack = new Stack<>();

    public static void funLoop(int[] shu, int targ, int cur) {
        if (cur == targ) {
            System.out.println(stack);
            return;
        }

        for (int j : shu) {
            stack.add(j);
            funLoop(shu, targ, cur + 1);
            stack.pop();
        }
    }

    public static void main(String[] args) {

        String[] str = {"3", "4", "5", "6", "7"};

        funLoopStr(str, 5, 0);

        System.out.println("====================");
        List<String> passwordList = new ArrayList<>();
        for (String s : stringList) {
            s = s.replace(",", "");
            s = s.replace("[", "");
            s = s.replace("]", "");
            s = s.replace(" ", "");
            passwordList.add(s);
        }

        for (String s : passwordList) {
            System.out.println("转换之后" + s);
        }

    }


    public static Stack<String> stackStr = new Stack<>();

    public static List<String> stringList = new ArrayList<>();

    public static void funLoopStr(String[] shu, int targ, int cur) {
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


    public void fLoop() {
        String str[] = {"A", "B", "C", "D", "E"};

        int nCnt = str.length;

        long nBit = factorial(nCnt);
        System.out.println("总共循环次数" + nBit);
        System.out.println("总共循环次数2" + factorial(nCnt));

        for (long i = 1; i <= nBit; i++) {
            for (int j = 0; j < nCnt; j++) {
                if ((i << (31 - j)) >> 31 == -1) {
                    System.out.print(str[j]);
                }
            }
            System.out.println("");
        }
    }
}
