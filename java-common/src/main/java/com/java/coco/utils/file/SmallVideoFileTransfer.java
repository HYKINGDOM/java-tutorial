package com.java.coco.utils.file;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.file.PathUtil;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static cn.hutool.core.io.FileUtil.readableFileSize;

/**
 * @author HY
 */
public class SmallVideoFileTransfer {

    private final String SMALL_VIDEO_MP4 = "mp4";

    private final Double FILE_SIZE = Double.valueOf("20");

    public void smallVideoFileListTransfer(File file) {

        File[] files = file.listFiles();
        String filePath = file.getAbsolutePath() + File.separator + "短视频";
        Path path = Paths.get(filePath);

        if (!PathUtil.exists(path, false)) {
            PathUtil.mkdir(path);
            System.out.println("文件夹创建成功");
        }

        System.out.println("====================================");
        if (files != null) {
            List<File> fileList = Arrays.stream(files).filter(File::isFile).collect(Collectors.toList());
            for (File rootPathFile : fileList) {
                if (SMALL_VIDEO_MP4.equals(FileTypeUtil.getType(rootPathFile)) && fileSizeValid(readableFileSize(rootPathFile))) {
                    System.out.println(rootPathFile.toPath());
                    System.out.println("文件大小: " + readableFileSize(rootPathFile));
                    PathUtil.move(rootPathFile.toPath(), path, true);
                    System.out.println("文件移动成功");
                }
            }
        }
    }

    private boolean fileSizeValid(String fileLength) {
        String trim = fileLength.trim().replace(",", "");
        String substring = null;
        boolean flag = false;
        if (trim.contains("GB")) {
            substring = trim.substring(0, trim.length() - 2).replace(".", "").trim().concat("000");
        } else if (trim.contains("MB")) {
            substring = trim.substring(0, trim.length() - 2);
        } else if (trim.contains("KB")) {
            flag = true;
            substring = trim.substring(0, trim.length() - 2).trim();
        } else {
            return false;
        }
        Double aDouble = null;
        if (flag) {
            aDouble = Double.parseDouble(substring) / 1000;
        } else {
            aDouble = Double.valueOf(substring);
        }
        int compareTo = aDouble.compareTo(FILE_SIZE);
        return compareTo < 0;
    }


}
