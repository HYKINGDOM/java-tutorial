package com.java.coco.utils.file;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.java.coco.utils.file.constant.FileTypeConstant.VIDEO_EXTENSION;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author HY
 */
public class PrivateVideoClassFile {


    private List<String> CONSTANT_FILE_TYPE = new ArrayList<>(Arrays.asList(VIDEO_EXTENSION));

    public Map<Integer, List<String>> countPrivateVideoClassFile() {
        File file = FileUtil.file("L:\\win\\日韩ready");
        File[] files = file.listFiles();
        Map<Integer, List<String>> listMap = new HashMap<>();
        List<String> stringList = new ArrayList<>();
        if (files != null) {
            List<File> fileList = Arrays.stream(files).filter(File::isFile).collect(Collectors.toList());
            for (File rootPathFile : fileList) {
                if (CONSTANT_FILE_TYPE.contains(FileTypeUtil.getType(rootPathFile))) {
                    stringList.add(className(rootPathFile.getName()));
                }
            }
        }
        listMap = stringList.stream().distinct().collect(groupingBy(String::length));

        for (Map.Entry<Integer, List<String>> integerListEntry : listMap.entrySet()) {
            System.out.println("length: " + integerListEntry.getKey());
            integerListEntry.getValue().forEach(e -> System.out.print(",\"" + e + "\""));
            System.out.println("\n");
            System.out.println("Count: " + integerListEntry.getValue().size());
            System.out.println("====================");
        }

        return listMap;
    }


    public String className(String str) {

        int num = countOfStr(str, "-");

        if (num == 1) {
            str = str.substring(0, str.indexOf("-"));
        } else if (num == 0) {
            int length = str.length();
            if (str.endsWith("C")) {
                str = str.substring(0, str.indexOf("C") - 1);
            } else {
                str = str.substring(0, str.indexOf(".") - 1);
            }

            for (int i = 0; i < length; i++) {
                str = replaceNum(str);
            }

        } else if (num == 2) {
            str = str.substring(0, str.indexOf("-"));
        }
        return str;
    }

    private int countOfStr(String str, String key) {
        char[] charArray = str.toCharArray();
        int flag = 0;
        for (char c : charArray) {
            if (key.equals(String.valueOf(c))) {
                flag++;
            }
        }
        return flag;
    }

    private String replaceNum(String str) {

        if (str.contains("0")) {
            str = str.replace("0", "");
        }

        if (str.contains("1")) {
            str = str.replace("1", "");
        }

        if (str.contains("2")) {
            str = str.replace("2", "");
        }

        if (str.contains("3")) {
            str = str.replace("3", "");
        }

        if (str.contains("4")) {
            str = str.replace("4", "");
        }

        if (str.contains("5")) {
            str = str.replace("5", "");
        }

        if (str.contains("6")) {
            str = str.replace("6", "");
        }

        if (str.contains("7")) {
            str = str.replace("7", "");
        }

        if (str.contains("8")) {
            str = str.replace("8", "");
        }

        if (str.contains("9")) {
            str = str.replace("9", "");
        }

        return str;
    }
}
