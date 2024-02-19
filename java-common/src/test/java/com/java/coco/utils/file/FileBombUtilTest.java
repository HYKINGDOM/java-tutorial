package com.java.coco.utils.file;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileBombUtilTest {

    @Test
    public void test_demo_01() {

        File bomb = new File("D:\\download\\zbsm.zip");
        File tempFile = new File("D:\\download\\3\\4");
        try {
            FileBombUtil.unzip(bomb, tempFile, FileBombUtil.FILE_LIMIT_SIZE * 60);
        } catch (IllegalArgumentException e) {
            if (FileBombUtil.FILE_LIMIT_SIZE_MSG.equalsIgnoreCase(e.getMessage())) {
                FileBombUtil.deleteDir(tempFile);
                System.out.println("原始文件太大");
            } else {
                System.out.println("错误的压缩文件格式");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}