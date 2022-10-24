package com.java.coco.utils.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java.coco.utils.file.util.DirectorUtil.clearNullFileDir;
import static com.java.coco.utils.file.util.DirectorUtil.createDirector;
import static com.java.coco.utils.file.util.DirectorUtil.createMoreFiles;

class DirectorUtilTest {

    @Test
    void test_clearNullFileDir() throws IOException {

//        createMoreFiles();
//
//        clearNullFileDir("I:\\data");
    }


    @Test
    void test_create_file() {

        String path = "I:\\迅雷下载";

        Map<String, List<File>> listMap = new HashMap<>();

        createDirector(path, listMap);
    }

    @Test
    void test_create_file_demo_01() throws IOException {

        createMoreFiles();
    }




}