package com.java.coco.utils.file;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.RandomUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static cn.hutool.core.io.FileUtil.exist;
import static cn.hutool.core.io.FileUtil.readableFileSize;
import static cn.hutool.core.io.FileUtil.rename;
import static com.java.coco.utils.file.FileTypeUtils.FILTER_STRING_LIST;
import static com.java.coco.utils.file.FileTypeUtils.TXT_FILE_SYMBOL_LIST;

/**
 * @author HY
 */
public class TXTRename {


    private static final String SMALL_TXT = "txt";

    private static final String CAPITAL_TXT = "TXT";

    private static final String FILE_TXT_CHM = "chm";

    private static final String ONLY_NUMBER = "[0-9]*";

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
                    //TODO  可以通过文件大小的对比去重
                    System.out.println("文件大小: " + readableFileSize(rootPathFile));
                    String rootPathFileName = rootPathFile.getName();
                    System.out.println("旧文件名: " + rootPathFileName);
                    rootPathFileName = replaceName(rootPathFileName);
                    if (!exist(rootPathFile.getPath())) {
                        rename(rootPathFile, rootPathFileName, false);
                    }else {

                        rename(rootPathFile, rootPathFileName, false);
                    }
                    System.out.println("新文件名: " + rootPathFileName);
                }
            }
        }
    }

    private String replaceName(String fileName) {
        String result = fileName.replaceAll(" ", "");

        int lastIndexOf = result.lastIndexOf(".");
        String fileType = result.substring(lastIndexOf);
        String result01 = result.substring(0, lastIndexOf);

        String substring = result01.substring(result01.length() - 5);
        if (isNumber(substring)) {
            result01 = result01.substring(0, result01.length() - 5);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : result01.toCharArray()) {
            String valueOf = String.valueOf(ch);
            if (!TXT_FILE_SYMBOL_LIST.contains(valueOf)) {
                stringBuilder.append(ch);
            }
        }

        result01 = stringBuilder.toString();

        for (String str : FILTER_STRING_LIST) {
            if (result01.contains(str)) {
                result01 = replaceString(result01, str);
            }
        }

        return result01 + fileType;
    }


    public boolean isNumber(String str) {
        if (str.length() > 0) {
            Pattern pattern = Pattern.compile(ONLY_NUMBER);
            Matcher isNum = pattern.matcher(str);
            return isNum.matches();
        }
        return false;
    }


    private String replaceString(String fileName, String symbol) {
        if (fileName.contains(symbol)) {
            fileName = fileName.replace(symbol, "");
        }

        return fileName;
    }
}
