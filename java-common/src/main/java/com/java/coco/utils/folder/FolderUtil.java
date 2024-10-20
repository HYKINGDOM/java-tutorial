package com.java.coco.utils.folder;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author meta
 */
public class FolderUtil {

    public static List<File> folderDirectoryLists(File[] file, List<File> files) {

        if (file != null) {
            files.addAll(Arrays.stream(file).filter(File::isFile).collect(Collectors.toList()));
        }

        if (file != null) {
            List<File> collect = Arrays.stream(file).filter(File::isDirectory).collect(Collectors.toList());
            for (File file1 : collect) {
                File[] listFiles = file1.listFiles();
                folderDirectoryLists(listFiles, files);
            }
        }
        return files;
    }
}
