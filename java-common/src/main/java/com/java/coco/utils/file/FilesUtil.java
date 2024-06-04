package com.java.coco.utils.file;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author HY
 */
public class FilesUtil {

    public static List<File> fileDirectoryLists(File[] file, List<File> files) {

        if (file != null) {
            files.addAll(Arrays.stream(file).filter(File::isFile).collect(Collectors.toList()));
        }

        if (file != null) {
            List<File> collect = Arrays.stream(file).filter(File::isDirectory).collect(Collectors.toList());
            for (File file1 : collect) {
                File[] listFiles = file1.listFiles();
                fileDirectoryLists(listFiles, files);
            }
        }
        return files;
    }

    /**
     * 遍历获取指定文件目录下的所有文件
     *
     * @param path 指定文件目录
     * @return 当前目录下的所有文件
     */
    public static Map<String, List<File>> fileFromDirectoryToFiles(String path) {

        File file = FileUtil.file(path);
        File[] files = file.listFiles();

        List<File> fileList = new ArrayList<>();

        fileDirectoryList(files, fileList);

        return fileList.stream().collect(Collectors.groupingBy(e -> FileTypeUtil.getType(e).toUpperCase()));
    }

    /**
     * 递归获取文件
     *
     * @param file  文件
     * @param files 目录下所有文件集合
     */
    private static void fileDirectoryList(File[] file, List<File> files) {

        if (file != null) {
            files.addAll(Arrays.stream(file).filter(File::isFile).collect(Collectors.toList()));
        }

        if (file != null) {
            List<File> collect = Arrays.stream(file).filter(File::isDirectory).collect(Collectors.toList());
            for (File file1 : collect) {
                File[] listFiles = file1.listFiles();
                fileDirectoryList(listFiles, files);
            }
        }
    }

    /**
     * 遍历删除空文件夹
     *
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDir(file);
                } else {
                    file.delete();
                }
            }
        }
        return dir.delete();
    }
}
