package com.java.coco.utils.zip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class TxtExport {

    private static String path = "F:/";
    private static String filenameTemp;

    /**
     * 创建文件
     *
     * @throws IOException
     */
    public boolean creatTxtFile(String name) {
        boolean flag = false;
        filenameTemp = path + name + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            try {
                filename.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            flag = true;
        }
        return flag;
    }

    /**
     * 写文件
     *
     * @param stringList 新内容
     * @throws IOException
     */
    public boolean writeTxtFile(List<String> stringList) {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuilder buf = new StringBuilder();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf.append(System.getProperty("line.separator"));
            }
            for (String s : stringList) {
                s = s + "\r\n";
                buf.append(s);
            }
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

}