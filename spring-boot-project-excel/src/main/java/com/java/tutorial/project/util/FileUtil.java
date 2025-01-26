package com.java.tutorial.project.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author kscs
 */
public class FileUtil {
    /**
     * 以byte[]方式读取文件
     *
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    public static byte[] readFileByBytes(String fileName) throws IOException {
        try (InputStream in = new BufferedInputStream(new FileInputStream(fileName));
            ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            byte[] tempbytes = new byte[in.available()];
            for (int i = 0; (i = in.read(tempbytes)) != -1; ) {
                out.write(tempbytes, 0, i);
            }
            return out.toByteArray();
        }
    }

    /**
     * 向文件写入byte[]
     *
     * @param fileName 文件名
     * @param bytes    字节内容
     * @param append   是否追加
     * @throws IOException
     */
    public static void writeFileByBytes(String fileName, byte[] bytes, boolean append) throws IOException {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName, append))) {
            out.write(bytes);
        }
    }

    /**
     * 从文件开头向文件写入byte[]
     *
     * @param fileName 文件名
     * @param bytes    字节
     * @throws IOException
     */
    public static String writeFileByBytes(String fileName, byte[] bytes) throws IOException {
        writeFileByBytes(fileName, bytes, false);
        return fileName;
    }

}

