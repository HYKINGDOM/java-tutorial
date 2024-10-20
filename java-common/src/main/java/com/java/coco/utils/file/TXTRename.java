package com.java.coco.utils.file;

import cn.hutool.core.io.FileTypeUtil;

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

/**
 * @author meta
 */
public class TXTRename {

    public static final List<String> FILTER_STRING_LIST =
        List.of("SOUSHU555.NET", "SOUSHU555.COM", "SOUSHU555.ORG", "SOUSHU2021.COM", "2048社区-BIG2048.COM", "THZU.CC",
            "2048社区", "BIG2048.COM", "BT-BTT.COM", "AI高清2K修复", "BBS.YZKOF.COM", "UUC82.COM", "1024核工厂",
            "FUN2048.COM", "THZU.CCTM", "THZU.CC", "原创", "重磅泄露", "最新", "更新", "NYAP2P.COM", "BT-BTT.COM",
            "SOAV.COM", "UNCENSORED", "LEAKED", ".HD", "每日更新", "每日", "799DVD.COM", "606DVD.COM", "136DVD.COM",
            "136DVD.COM", "909DVD.COM", "132DVD.COM", "FULIBUS.NET", "91视频", "91自拍", "国产自拍", "KiKi", "魔性论坛",
            "WWW.MOX.LIFE", "2048论坛BBS2048.ORG", "91大神猫先生千人斩之", "SEXFLEXVIDEO", "YONITALE.COM",
            "BBS2048.ORG", ".COM", "ONLYFANS", "EAPK.XYZ", "❤", "GUOCHAN2048", "ThZu.Cc", "[", "]", "", "【", "】",
            "8899XX.XYZ", "jav20s8.com", "guochan2048.com", "soushu555.net", "soushu555.com", "soushu555.org",
            "soushu2021.com", "soushu2022.com", "搜书吧", "网址");
    public static final List<String> TXT_FILE_SYMBOL_LIST =
        List.of(" ", "@", "「", "」", "《", "》", "【", "】", "[", "]", "『", "』", "〖", "〗", "▌", "(", ")", "（", "）", "。", "“",
            "”", "，", "~", "#", "!", "！", "+", "=");
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
                if (SMALL_TXT.equals(FileTypeUtil.getType(rootPathFile)) || CAPITAL_TXT.equals(
                    FileTypeUtil.getType(rootPathFile)) || FILE_TXT_CHM.equals(FileTypeUtil.getType(rootPathFile))) {
                    //TODO  可以通过文件大小的对比去重
                    System.out.println("文件大小: " + readableFileSize(rootPathFile));
                    String rootPathFileName = rootPathFile.getName();
                    System.out.println("旧文件名: " + rootPathFileName);
                    rootPathFileName = replaceName(rootPathFileName);
                    if (!exist(rootPathFile.getPath())) {
                        rename(rootPathFile, rootPathFileName, false);
                    } else {

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
