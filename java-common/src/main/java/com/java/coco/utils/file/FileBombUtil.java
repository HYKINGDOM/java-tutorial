package com.java.coco.utils.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 文件炸弹工具类
 *
 * @author meta
 */
public class FileBombUtil {

    /**
     * 限制文件大小 1M（限制单位：B）[1M=1024KB 1KB=1024B]
     */
    public static final Long FILE_LIMIT_SIZE = 1024 * 1024 * 1L;

    /**
     * 文件超限提示
     */
    public static final String FILE_LIMIT_SIZE_MSG = "The file size exceeds the limit";

    /**
     * 解压文件（带限制解压文件大小策略）
     *
     * @param file         压缩文件
     * @param outputfolder 解压后的文件目录
     * @param size         限制解压之后的文件大小（单位：B），示例 3M：1024 * 1024 * 3L （FileBombUtil.FILE_LIMIT_SIZE * 3）
     * @throws Exception IllegalArgumentException 超限抛出的异常
     *                   注意：业务层必须抓取IllegalArgumentException异常，如果msg等于FILE_LIMIT_SIZE_MSG 要考虑后面的逻辑，比如告警
     */
    public static void unzip(File file, File outputfolder, Long size) throws Exception {
        ZipFile zipFile = new ZipFile(file);
        FileOutputStream fos = null;
        try {
            Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
            long zipFileSize = 0L;
            ZipEntry entry;
            while (zipEntries.hasMoreElements()) {
                // 获取 ZIP 文件的下一个条目
                entry = zipEntries.nextElement();
                // 将解缩大小累加到 zipFileSize 变量
                zipFileSize += entry.getSize();
                // 判断解压文件累计大小是否超过指定的大小
                if (zipFileSize > size) {
                    deleteDir(outputfolder);
                    throw new IllegalArgumentException(FILE_LIMIT_SIZE_MSG);
                }
                File unzipped = new File(outputfolder, entry.getName());
                if (entry.isDirectory() && !unzipped.exists()) {
                    unzipped.mkdirs();
                    continue;
                } else if (!unzipped.getParentFile().exists()) {
                    unzipped.getParentFile().mkdirs();
                }

                fos = new FileOutputStream(unzipped);
                InputStream in = zipFile.getInputStream(entry);

                byte[] buffer = new byte[4096];
                int count;
                while ((count = in.read(buffer, 0, buffer.length)) != -1) {
                    fos.write(buffer, 0, count);
                }
            }
        } finally {
            if (null != fos) {
                fos.close();
            }
            if (null != zipFile) {
                zipFile.close();
            }
        }

    }

    /**
     * 递归删除目录文件
     *
     * @param dir 目录
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < Objects.requireNonNull(children).length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

}

