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
import static com.java.coco.utils.file.FileTypeUtils.FILE_SYMBOL_LIST;
import static com.java.coco.utils.file.FileTypeUtils.FILTER_STRING_LIST;

/**
 * @author HY
 */
public class TXTRename {


    private static final String SMALL_TXT = "txt";

    private static final String CAPITAL_TXT = "TXT";

    private static final String FILE_TXT_CHM = "chm";

    private static final int FOREACH_NUMBER = 5;

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
        for (int i = 0; i < FOREACH_NUMBER; i++) {
            FILE_SYMBOL_LIST.forEach(e -> replaceString(fileName, e));
            FILTER_STRING_LIST.forEach(e -> replaceString(fileName, e));
        }
        return fileName;
    }


    private String replaceString(String fileName, String symbol) {
        fileName = fileName.trim();
        if (fileName.contains(symbol)) {
            fileName = fileName.replace(symbol, "");
        }

        return fileName;
    }
}
