package com.java.coco.utils.file;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.file.PathUtil;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.java.coco.utils.file.constant.FileTypeConstant.WEB_EXTENSION;

/**
 * @author meta
 */
public class HtmlFileTransfer {

    public void mHtmlFileTransfer(File file) {

        File[] files = file.listFiles();
        if (files != null) {
            String filePath = file.getAbsolutePath() + File.separator + "另存为网页";
            Path path = Paths.get(filePath);

            if (!PathUtil.exists(path, false)) {
                PathUtil.mkdir(path);
                System.out.println("文件夹创建成功");
            }

            List<File> fileList = Arrays.stream(files).filter(File::isFile).collect(Collectors.toList());
            List<String> stringList = new ArrayList<>(Arrays.asList(WEB_EXTENSION));
            for (File rootPathFile : fileList) {
                if (stringList.contains(FileTypeUtil.getType(rootPathFile))) {
                    System.out.println(rootPathFile.toPath());
                    PathUtil.move(rootPathFile.toPath(), path, true);
                    System.out.println("文件移动成功");
                }
            }
        }
    }
}
