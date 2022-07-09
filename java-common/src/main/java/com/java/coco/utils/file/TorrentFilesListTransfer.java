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

import static com.java.coco.utils.file.FileTypeUtils.TORRENT_EXTENSION;

/**
 * @author HY
 */
public class TorrentFilesListTransfer {

    public void torrentFileListTransfer(File file) {
        File[] files = file.listFiles();
        List<String> stringList = new ArrayList<>(Arrays.asList(TORRENT_EXTENSION));
        if (files != null) {
            String filePath = file.getAbsolutePath() + File.separator + "种子";
            Path path = Paths.get(filePath);

            if (!PathUtil.exists(path, false)) {
                PathUtil.mkdir(path);
                System.out.println("文件夹创建成功");
            }

            List<File> fileList = Arrays.stream(files).filter(File::isFile).collect(Collectors.toList());
            int size = 0;
            for (File fileTypes : fileList) {
                if (stringList.contains(FileTypeUtil.getType(fileTypes))) {
                    System.out.println(fileTypes.getName());
                    PathUtil.move(fileTypes.toPath(), path, true);
                    size++;
                }
            }
            System.out.println("总数: " + size);
        }
    }
}
