package com.java.coco.utils.file.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.java.coco.utils.file.util.FileMoveUtil.moveFileToTargetIndex;

class FileMoveUtilTest {

    private FileMoveUtil fileMoveUtil;

    @BeforeEach
    void setUp() {
        fileMoveUtil = new FileMoveUtil();
    }

    @Test
    void test_moveFileToTargetIndex_demo() {

        String rootFilePath = "I:\\data";

        String targetFilePath = "I:\\data\\test1\\test2\\test3\\test6.log";

        moveFileToTargetIndex(rootFilePath, new File(targetFilePath));

    }

    @Test
    void test_move_file_demo_02() {
        String fromPath = "I:\\data\\test1\\test2\\test3\\test3.log";
        String toPath = "I:\\data";
        System.out.println("移动文件：从路径 " + fromPath + " 移动到路径 " + toPath);
        File file = new File(fromPath);
        if (file.isFile()) {
            File toFile = new File(toPath + "\\" + file.getName());
            if (toFile.exists()) {
                System.out.println("文件已存在");
            } else {
                file.renameTo(toFile);
                System.out.println("移动文件成功");
            }
        }
    }
}