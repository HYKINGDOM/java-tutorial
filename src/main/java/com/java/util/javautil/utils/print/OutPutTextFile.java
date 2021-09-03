package com.java.util.javautil.utils.print;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

public class OutPutTextFile {

    public static void main(String[] args) {
        String str = "{\n" +
                "\t\"url\": \"https://qqe2.com\",\n" +
                "\t\"name\": \"欢迎使用JSON在线解析编辑器\",\n" +
                "\t\"array\": {\n" +
                "\t\t\"JSON校验\": \"http://jsonlint.qqe2.com/\",\n" +
                "\t\t\"Cron生成\": \"http://cron.qqe2.com/\",\n" +
                "\t\t\"JS加密解密\": \"http://edit.qqe2.com/\"\n" +
                "\t},\n" +
                "\t\"boolean\": true,\n" +
                "\t\"null\": null,\n" +
                "\t\"number\": 123,\n" +
                "\t\"object\": {\n" +
                "\t\t\"a\": \"b\",\n" +
                "\t\t\"c\": \"d\",\n" +
                "\t\t\"e\": \"f\"\n" +
                "\t}\n" +
                "}";
        OutPutTextFile outPutTextFile = new OutPutTextFile();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        outPutTextFile.outPutText(sb);

    }


    public void outPutText(StringBuilder stringBuilder) {
        File file = new File("d:" + File.separator + Calendar.getInstance().getTimeInMillis() + ".txt");
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            byte[] b = stringBuilder.toString().getBytes();
            for (byte value : b) {
                out.write(value);
            }
            out.close();
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
