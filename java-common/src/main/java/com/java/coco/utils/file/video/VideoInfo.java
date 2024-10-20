package com.java.coco.utils.file.video;

import lombok.Builder;
import lombok.Data;

/**
 * @author meta
 */
@Data
@Builder
public class VideoInfo {
    /**
     * 总帧数
     **/
    private int lengthInFrames;
    /**
     * 帧率
     **/
    private double frameRate;
    /**
     * 时长
     **/
    private double duration;
    /**
     * 视频编码
     */
    private String videoCode;
    /**
     * 音频编码
     */
    private String audioCode;
    private int width;
    private int height;
    private int audioChannel;
    private String md5;
    /**
     * 音频采样率
     */
    private Integer sampleRate;
}
