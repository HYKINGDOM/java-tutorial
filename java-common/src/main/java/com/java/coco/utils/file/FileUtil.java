package com.java.coco.utils.file;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HY
 */
public class FileUtil {


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
}
