package com.java.coco.utils.file.util;

import com.java.coco.utils.file.video.VideoInfo;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;

import java.io.File;

/**
 * @author HY
 */
@Slf4j
public class VideoInfoUtil {

    public static VideoInfo getVideoInfo(File file) {
        VideoInfo videoInfo = VideoInfo.builder().build();
        FFmpegFrameGrabber grabber = null;
        try {
            grabber = new FFmpegFrameGrabber(file);
            // 启动 FFmpeg
            grabber.start();

            // 读取视频帧数
            videoInfo.setLengthInFrames(grabber.getLengthInVideoFrames());

            // 读取视频帧率
            videoInfo.setFrameRate(grabber.getVideoFrameRate());

            // 读取视频秒数
            videoInfo.setDuration(grabber.getLengthInTime() / 1000000.00);

            // 读取视频宽度
            videoInfo.setWidth(grabber.getImageWidth());

            // 读取视频高度
            videoInfo.setHeight(grabber.getImageHeight());


            videoInfo.setAudioChannel(grabber.getAudioChannels());

            videoInfo.setVideoCode(grabber.getVideoCodecName());

            videoInfo.setAudioCode(grabber.getAudioCodecName());
            //String md5 = MD5Util.getMD5ByInputStream(new FileInputStream(file));

            videoInfo.setSampleRate(grabber.getSampleRate());
            return videoInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (grabber != null) {
                    // 此处代码非常重要，如果没有，可能造成 FFmpeg 无法关闭
                    grabber.stop();
                    grabber.release();
                }
            } catch (FFmpegFrameGrabber.Exception e) {
                log.error("getVideoInfo grabber.release failed 获取文件信息失败：{}", e.getMessage());
            }
        }
    }


}
