package com.java.util.javautil.interview.hw.huawei.test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String stringS = sc.nextLine();
        String stringL = sc.nextLine();

        if (null == stringS || ("").equals(stringS) || null == stringL || ("").equals(stringL)) {
            System.out.println(-1);
            return;
        }

        char[] charsS = stringS.toCharArray();
        char[] charsL = stringL.toCharArray();
        if(charsL.length < charsS.length){
            System.out.println(-1);
            return;
        }

        List<Integer> integerList = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < charsS.length; i++) {
            for (int j = 0; j < charsL.length; j++) {
                if (charsL[j] == charsS[i]){
                    if (!hashMap.containsKey(String.valueOf(charsS[j]))){
                        hashMap.put(String.valueOf(charsS[i]),j);
                        integerList.add(j);
                    }else {
                        System.out.println(-1);
                    }
                }
            }
        }
        if (integerList.size()==0){
            System.out.println(-1);
            return;
        }
        String string = integerList.toString();
        System.out.println(string);
        integerList.sort(null);
        String string1 = integerList.toString();
        System.out.println("=========");
        System.out.println(string1);
        if (string.equals(string1)){
            System.out.println(integerList.get(integerList.size()-1));
            return;
        }
        System.out.println(-1);

    }
}
