package com.java.util.javautil.utils.file;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static cn.hutool.core.io.FileUtil.rename;
import static com.java.util.javautil.utils.file.FileTypeUtils.VIDEO_EXTENSION;

/**
 * @author HY
 */
public class VideoFileSwitch {


    public void videoFile() {
        File file = FileUtil.file("L:\\win\\日韩ready");
        File[] files = file.listFiles();

        if (files != null) {
            List<File> fileList = Arrays.stream(files).filter(File::isFile).collect(Collectors.toList());
            List<String> stringList = new ArrayList<>(Arrays.asList(VIDEO_EXTENSION));
            for (File rootPathFile : fileList) {
                if (stringList.contains(FileTypeUtil.getType(rootPathFile))) {
                    System.out.println("修改前" + rootPathFile.getName());
                    String rootPathFileName = rootPathFile.getName();
                    rootPathFileName = replaceName(rootPathFileName);
                    System.out.println("修改中" + rootPathFileName);
                    rename(rootPathFile, rootPathFileName, false);
                    System.out.println("修改后" + rootPathFile.getName());
                    System.out.println("====================");
                }
            }
        }
    }

    private String replaceName(String fileName) {
        fileName = fileName.trim().toUpperCase();
        if (fileName.contains("《")) {
            fileName = fileName.replace("《", "");
        }
        if (fileName.contains("》")) {
            fileName = fileName.replace("》", "");
        }

        if (fileName.contains("【")) {
            fileName = fileName.replace("【", "");
        }

        if (fileName.contains("】")) {
            fileName = fileName.replace("】", "");
        }

        if (fileName.contains("[")) {
            fileName = fileName.replace("[", "");
        }

        if (fileName.contains("]")) {
            fileName = fileName.replace("]", "");
        }

        if (fileName.contains("--")) {
            fileName = fileName.replace("--", "");
        }

        if (fileName.contains("_")) {
            fileName = fileName.replace("_", "-");
        }

        if (fileName.contains("soushu555.com")) {
            fileName = fileName.replace("soushu555.com", "");
        }

        if (fileName.contains("soushu555.org")) {
            fileName = fileName.replace("soushu555.org", "");
        }

        if (fileName.contains("soushu555.net")) {
            fileName = fileName.replace("soushu555.net", "");
        }

        if (fileName.contains("soushu2021.com")) {
            fileName = fileName.replace("soushu2021.com", "");
        }

        if (fileName.contains("搜书吧网址")) {
            fileName = fileName.replace("搜书吧网址", "");
        }

        if (fileName.contains("11")) {
            fileName = fileName.replace("11", "1-1");
        }

        return fileName;
    }
}
