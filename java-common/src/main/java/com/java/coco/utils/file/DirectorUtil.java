package com.java.coco.utils.file;

import cn.hutool.core.io.file.PathUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.java.coco.utils.file.constant.FileTypeConstant.getAllFileType;

/**
 * @author meta
 */
public class DirectorUtil {

    private static int iFile = 0;

    public static void clearNullFileDir(String path) {
        File file = new File(path);
        clear(file);
        String format = String.format("总共清理 %d 个空文件夹.", iFile);
        System.out.println(format);
    }

    private static void clear(File dir) {
        File[] dirs = dir.listFiles();
        for (int i = 0; i < Objects.requireNonNull(dirs).length; i++) {
            if (dirs[i].isDirectory()) {
                clear(dirs[i]);
            }
        }
        if (dir.isDirectory() && dir.delete()) {
            iFile++;
        }
        //System.out.println(dir + "清理成功");
    }

    public static Map<String, List<String>> createDirector(String path, Map<String, List<File>> listMap) {

        Map<String, List<String>> allFileType = getAllFileType();

        String rootPath = path + File.separator + "整理";

        List<String> createFilePath = new ArrayList<>();
        createFilePath.add(rootPath);

        Set<String> keySet = listMap.keySet();

        for (Map.Entry<String, List<String>> stringListEntry : allFileType.entrySet()) {
            String key = stringListEntry.getKey();
            List<String> value = stringListEntry.getValue();
            String curPath = rootPath + File.separator + key;
            createFilePath.add(curPath);
            for (String str : value) {
                if (keySet.contains(str)) {
                    String curPathNext = curPath + File.separator + str;
                    createFilePath.add(curPathNext);
                }
            }
        }

        for (String createPath : createFilePath) {
            Path filePath = Paths.get(createPath);
            if (!PathUtil.exists(filePath, false)) {
                PathUtil.mkdir(filePath);
                System.out.println("文件夹创建成功: " + createPath);
            }
        }

        return allFileType;
    }

    public static void createMoreFiles() throws IOException {
        Files.createDirectories(Paths.get("I:\\data\\test1\\test2\\test3\\test4\\test5\\"));
        Files.write(Paths.get("I:\\data\\test1\\test2\\test2.log"), "hello".getBytes());
        Files.write(Paths.get("I:\\data\\test1\\test2\\test3\\test3.log"), "hello".getBytes());
    }

}
