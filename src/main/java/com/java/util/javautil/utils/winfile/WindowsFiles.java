package com.java.util.javautil.utils.winfile;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.PathUtil;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WindowsFiles {

    private static final String filename = "换脸系列";

    private static final String filetype = "mhtml";

    private static final String filePath = "J:\\迅雷下载\\另存为网页";

    private static final String filePathName = "另存为网页";


    public static void main(String[] args) {
        File file = FileUtil.file("J:\\迅雷下载");
        File[] files = file.listFiles();
        List<File> fileDirectoryList = Arrays.stream(files).filter(File::isDirectory).collect(Collectors.toList());
        Path path = Paths.get(filePath);

        for (File directoryFile : fileDirectoryList) {
            System.out.println("这是文件夹：" + directoryFile.getName());
        }

        if (!PathUtil.exists(path, false)) {
            PathUtil.mkdir(path);
            System.out.println("文件夹创建成功");
        }

        System.out.println("====================================");
        List<File> fileList = Arrays.stream(files).filter(File::isFile).collect(Collectors.toList());
        for (File rootPathFile : fileList) {
            if (filetype.equals(FileTypeUtil.getType(rootPathFile))) {
                System.out.println(path.toString());
                System.out.println(rootPathFile.toPath().toString());
                PathUtil.move(rootPathFile.toPath(), path, true);
                System.out.println("文件移动成功");
            }
        }
    }
}
