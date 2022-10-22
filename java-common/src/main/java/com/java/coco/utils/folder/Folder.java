package com.java.coco.utils.folder;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Folder {

    private List<File> fileList = new ArrayList<>();

    public static final List<String> FILE_SYMBOL_LIST = List.of(" ", "-", "@", "「", "」", "《", "》", "【", "】", "[", "]", "『"
            , "』", "〖", "〗", "▌", "(", ")", "（", "）", "。", "“", "”", "，", "~", "#", "!", "！", "+", "=", "⊙");


    public static void main(String[] args) {
        Folder folder = new Folder();
        folder.videoFile();
    }


    public void videoFile() {
        File file = FileUtil.file("K:\\新建文件夹\\写真图集");
        File[] files = file.listFiles();


        List<File> fileDirectoryLists = Arrays.stream(files).filter(File::isDirectory).collect(Collectors.toList());
        //List<File> fileDirectoryLists = folderDirectoryLists(files, fileList);

        System.out.println("==========---------------------------==========");
        for (File file1 : fileDirectoryLists) {
            String file1Name = file1.getName().replaceAll(" ", "");

            for (char ch : file1Name.toCharArray()) {
                String value = String.valueOf(ch);
                if (FILE_SYMBOL_LIST.contains(value)) {
                    System.out.println("文件夹名修改前： " + file1Name);
                    file1Name = replaceName(file1Name, value);
                    System.out.println("文件夹名修改后： " + file1Name);
                }
            }
        }

//        for (File rootPathFile : fileDirectoryLists) {
//            System.out.println(rootPathFile.getName());
//
//        }
    }


    private String replaceName(String fileName, String strKey) {
        fileName = fileName.replace(strKey, "");
        return fileName.trim();
    }

    private String fixFileName(String filePath, String newFileName) {
        File f = new File(filePath);

        newFileName = newFileName.trim();

        // 文件名不能为空
        if ("".equals(newFileName)) {
            return null;
        }

        String newFilePath = null;
        // 判断是否为文件夹
        if (f.isDirectory()) {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\" + newFileName;
        }

        File nf = new File(newFilePath);
        try {
            // 修改文件名
            f.renameTo(nf);
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
        return newFilePath;
    }
}
