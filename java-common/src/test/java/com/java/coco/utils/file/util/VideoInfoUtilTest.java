package com.java.coco.utils.file.util;

import com.java.coco.utils.file.video.VideoInfo;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.java.coco.utils.file.util.VideoInfoUtil.getVideoInfo;
import static org.junit.jupiter.api.Assertions.*;

class VideoInfoUtilTest {

    @Test
    public void test_get_video_info_demo_01() {
        String path = "L:\\迅雷下载\\hhd800.com@SSIS-560.mp4";

        File file = new File(path);
        VideoInfo videoInfo = getVideoInfo(file);

        System.out.println(videoInfo);

    }

}