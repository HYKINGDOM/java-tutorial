package com.java.coco.utils.file;

import java.io.File;

/**
 * @author meta
 */
public class WindowsFiles {

    public static void main(String[] args) {
        //        File file = FileUtil.file("J:\\迅雷下载");
        //        HtmlFileTransfer htmlFileTransfer = new HtmlFileTransfer();
        //        htmlFileTransfer.mHtmlFileTransfer(file);

        //        SmallVideoFileTransfer smallVideoFileTransfer = new SmallVideoFileTransfer();
        //        smallVideoFileTransfer.smallVideoFileListTransfer(file);

        File file = new File("J:\\小说");
        TXTRename txtRename = new TXTRename();
        txtRename.txtRenamed(file);
        //
        //        VideoFileSwitch videoFileSwitch = new VideoFileSwitch();
        //        videoFileSwitch.videoFile();

        //        PrivateVideoClassFile parentVideoClassFile = new PrivateVideoClassFile();
        //        Map<Integer, List<String>> listMap = parentVideoClassFile.countPrivateVideoClassFile();
        //
    }

}
