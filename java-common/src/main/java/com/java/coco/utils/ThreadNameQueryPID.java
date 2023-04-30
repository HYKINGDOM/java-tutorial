package com.java.coco.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ThreadNameQueryPID {

    public static void main(String[] args) {

        String processName = "MEmuHeadless.exe";
        //根据程序名获取PID
        String[] cmd01 = {"cmd", "/c", "FOR /F \"tokens=2,3*\"; %i  in ('tasklist /nh ^| find \"" + processName + "\"') do @echo %i"};

        ThreadNameQueryPID threadNameQueryPID = new ThreadNameQueryPID();
        List<String> stringList = threadNameQueryPID.byThreadNameQueryPID(cmd01);
        for (String s : stringList) {
            System.out.println(s);
        }
        //根据PID 查询端口
        String[] cmd02 = {"cmd", "/c", "netstat -ano | findstr " + stringList.get(0)};

        List<String> stringList1 = threadNameQueryPID.byThreadNameQueryPID(cmd02);

        for (String s : stringList) {
            System.out.println(s);
        }
    }

    private List<String> byThreadNameQueryPID(String[] cmmd) {
        List<String> stringList = new ArrayList<>();
        try {
            String str = null;
            Process process = Runtime.getRuntime().exec(cmmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((str = br.readLine()) != null) {
                stringList.add(str);
            }
            return stringList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }


}
