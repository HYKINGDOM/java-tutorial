package com.java.coco.utils.file;

import java.io.File;

import static com.java.coco.utils.file.FileNameUtils.rewriteRandomFileName;

/**
 * @author meta
 */
public class FileMoveUtil {

    public static void moveFileToTargetIndex(String toPath, File targetFile) {

        String fromPath = targetFile.getPath();
        System.out.println("移动文件：从路径 " + fromPath + " 移动到路径 " + toPath);
        File file = new File(fromPath);
        if (file.isFile()) {
            File toFile = new File(toPath + File.separator + file.getName());
            if (toFile.exists()) {
                file = rewriteRandomFileName(file);
                toFile = new File(toPath + File.separator + file.getName());
                System.out.println("文件存在,已修改文件名");
            }
            boolean renameTo = file.renameTo(toFile);
            System.out.println("移动文件: " + renameTo);
        }

    }
}
