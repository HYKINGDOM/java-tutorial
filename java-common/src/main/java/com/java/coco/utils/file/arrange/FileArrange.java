package com.java.coco.utils.file.arrange;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java.coco.utils.file.DirectorUtil.clearNullFileDir;
import static com.java.coco.utils.file.DirectorUtil.createDirector;
import static com.java.coco.utils.file.FileMoveUtil.moveFileToTargetIndex;
import static com.java.coco.utils.file.FileNameUtils.formatFileNameHandle;
import static com.java.coco.utils.file.FileNameUtils.rewriteFileName;
import static com.java.coco.utils.file.FilesUtil.fileFromDirectoryToFiles;

/**
 * 模版模式整理文件夹
 *
 * @author meta
 */
public abstract class FileArrange {

    public Map<String, List<File>> fileFromDirectoryToFiles = new HashMap<>();

    private String rootPath;

    private Map<String, List<String>> arrangeDirector = new HashMap<>();

    public final void execute(String path, List<String> fileType) {

        rootPath = path;

        getAllFile(path);

        fileMoveAndRenameHandle(fileType);

        fileDetailHandle();

        createFileHandle(path);

        fileGroupHandle();

        cleanNullDirector(path);

    }

    /**
     * 获取文件下所有的文件
     */
    private void getAllFile(String path) {

        fileFromDirectoryToFiles = fileFromDirectoryToFiles(path);

    }

    /**
     * 按照类型整理文件放到root文件目录,如果重复就重命名
     */
    public void fileMoveAndRenameHandle(List<String> fileType) {
        for (String typeStr : fileType) {
            if (fileFromDirectoryToFiles.containsKey(typeStr)) {
                List<File> files = fileFromDirectoryToFiles.get(typeStr);
                for (File file : files) {
                    System.out.println("修改前: " + file.getName());
                    String rootPathFileName = formatFileNameHandle(file.getName());
                    System.out.println("格式化文件名: " + rootPathFileName);
                    rewriteFileName(file, rootPathFileName);
                    System.out.println("修改后: " + file.getName());
                    moveFileToTargetIndex(rootPath, file);
                    System.out.println("====================");
                }
            }
        }
        //重新加载文件路径
        getAllFile(rootPath);
    }

    /**
     * 各不同类型的文件做特殊处理
     */
    public abstract void fileDetailHandle();

    /**
     * 创建文件夹
     */
    public void createFileHandle(String path) {
        System.out.println("创建文件夹");
        arrangeDirector = createDirector(path, fileFromDirectoryToFiles);
    }

    /**
     * 业务分类放到不同的文件夹
     */
    public void fileGroupHandle() {
        System.out.println("业务分类放到不同的文件夹");

        for (Map.Entry<String, List<File>> stringListEntry : fileFromDirectoryToFiles.entrySet()) {

        }

    }

    /**
     * 清理空文件夹
     */
    private void cleanNullDirector(String path) {
        System.out.println("清理空文件夹");
        clearNullFileDir(path);
    }

}
