package com.java.util.javautil.utils.file;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.PathUtil;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.java.util.javautil.utils.file.FileTypeUtils.TORRENT_EXTENSION;

/**
 * @author HY
 */
public class WindowsFiles {

    public static void main(String[] args) {
//        File file = FileUtil.file("J:\\迅雷下载");
//        HtmlFileTransfer htmlFileTransfer = new HtmlFileTransfer();
//        htmlFileTransfer.mHtmlFileTransfer(file);

//        SmallVideoFileTransfer smallVideoFileTransfer = new SmallVideoFileTransfer();
//        smallVideoFileTransfer.smallVideoFileListTransfer(file);

//        File file = FileUtil.file("J:\\迅雷下载\\小说");
//        TXTRename txtRename = new TXTRename();
//        txtRename.txtRenamed(file);
//
        VideoFileSwitch videoFileSwitch = new VideoFileSwitch();
        videoFileSwitch.videoFile();


//        PrivateVideoClassFile parentVideoClassFile = new PrivateVideoClassFile();
//        Map<Integer, List<String>> listMap = parentVideoClassFile.countPrivateVideoClassFile();
//
    }


}
