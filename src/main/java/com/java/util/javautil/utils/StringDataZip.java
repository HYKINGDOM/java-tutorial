package com.java.util.javautil.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * gzip字符串压缩
 * @author HY
 */
public class StringDataZip {

    /**
     * 使用gzip压缩字符串
     */
    public String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    }

    /**
     * 使用gzip解压缩
     */
    public String uncompress(String compressedStr) {
        if (compressedStr == null || compressedStr.length() == 0) {
            return compressedStr;
        }
        byte[] compressed = new byte[0];
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String decompressed = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(compressed);
             GZIPInputStream ginzip = new GZIPInputStream(in);) {
            byte[] buffer = new byte[1024];
            int offset;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decompressed;
    }
}
