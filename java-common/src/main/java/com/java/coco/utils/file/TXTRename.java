package com.java.coco.utils.file;

import cn.hutool.core.io.FileTypeUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static cn.hutool.core.io.FileUtil.readableFileSize;
import static cn.hutool.core.io.FileUtil.rename;

/**
 * @author HY
 */
public class TXTRename {


    private static final String SMALL_TXT = "txt";

    private static final String CAPITAL_TXT = "TXT";

    private static final String FILE_TXT_CHM = "chm";

    public void txtRenamed(File file) {

        File[] files = file.listFiles();
        if (files != null) {
            List<File> fileList = new ArrayList<>();
            List<File> fileDirectoryList = Arrays.stream(files).filter(File::isDirectory).collect(Collectors.toList());
            for (File fileDirectory : fileDirectoryList) {
                File[] listFiles = fileDirectory.listFiles();
                if (listFiles != null) {
                    Collections.addAll(fileList, listFiles);
                }
            }
            fileList.addAll(Arrays.stream(files).filter(File::isFile).collect(Collectors.toList()));
            for (File rootPathFile : fileList) {
                if (SMALL_TXT.equals(FileTypeUtil.getType(rootPathFile)) || CAPITAL_TXT.equals(FileTypeUtil.getType(rootPathFile)) || FILE_TXT_CHM.equals(FileTypeUtil.getType(rootPathFile))) {
                    System.out.println(rootPathFile.toPath());
                    System.out.println("文件大小: " + readableFileSize(rootPathFile));
                    String rootPathFileName = rootPathFile.getName();
                    rootPathFileName = replaceName(rootPathFileName);
                    rename(rootPathFile, rootPathFileName, false);
                    System.out.println("新文件名: " + rootPathFileName);
                }
            }
        }
    }


    private String replaceName(String fileName) {
        fileName = fileName.trim();
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
