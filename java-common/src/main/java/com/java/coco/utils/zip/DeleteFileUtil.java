package com.java.coco.utils.zip;

import java.io.File;

public class DeleteFileUtil {

    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {

            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);

        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean deleteDirectory(String dir) {
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = DeleteFileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else if (files[i].isDirectory()) {
                flag = DeleteFileUtil.deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {

            return false;
        }
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        //  String file = "c:/test/test.txt";
        //  DeleteFileUtil.deleteFile(file);
        //  System.out.println();

        String dir = "D:/home/web/upload/upload/files";
        DeleteFileUtil.deleteDirectory(dir);
        //  System.out.println();

        //  dir = "c:/test/test0";
        //  DeleteFileUtil.delete(dir);

    }

}