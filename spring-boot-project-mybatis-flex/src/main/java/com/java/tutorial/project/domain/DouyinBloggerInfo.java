package com.java.tutorial.project.domain;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * @author meta
 */
@Data
@Table(value = "douyin_blogger_info")
public class DouyinBloggerInfo {


    @Id(keyType = KeyType.Auto)
    private Long id;
    /**
     * 博主ID
     */
    private String bloggerId;
    /**
     * 博主名称
     */
    private String bloggerName;
    /**
     * 博主头像
     */
    private String bloggerAvatar;
    /**
     * 性别（1：男，2：女）
     */
    private Integer gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 星座
     */
    private String constellation;
    /**
     * 行业
     */
    private String industry;
    /**
     * 标签
     */
    private String label;
    /**
     * 粉丝数
     */
    private Integer followerCount;
    /**
     * 关注数
     */
    private Integer followingCount;
    /**
     * 获赞总数
     */
    private Integer totalLikes;
    /**
     * 评论总数
     */
    private Integer totalComments;
    /**
     * 分享总数
     */
    private Integer totalShares;
    /**
     * 视频总数
     */
    private Integer totalVideos;
    /**
     * 平均播放量
     */
    private Integer averageViews;
    /**
     * 平均点赞数
     */
    private Integer averageLikes;
    /**
     * 平均评论数
     */
    private Integer averageComments;
    /**
     * 平均分享数
     */
    private Integer averageShares;
    /**
     * 近30天视频GMV
     */
    @Column(value = "video_gmv_30d")
    private Double videoGmv30d;
    /**
     * 近90天视频GMV
     */
    @Column(value = "video_gmv_90d")
    private Double videoGmv90d;
    /**
     * 近180天视频GMV
     */
    @Column(value = "video_gmv_180d")
    private Double videoGmv180d;
    /**
     * 近365天视频GMV
     */
    @Column(value = "video_gmv_365d")
    private Double videoGmv365d;
    /**
     * 累计视频GMV
     */
    private Double totalGmv;
    /**
     * 最后发布视频时间
     */
    private Date lastVideoUploadTime;
    /**
     * 最后活跃时间
     */
    private Date lastActiveTime;
    /**
     * 账号创建时间
     */
    private Date accountCreateTime;
    /**
     * 是否认证（1：是，0：否）
     */
    private Integer verified;
    /**
     * 平台（默认：抖音）
     */
    private String platform;
    /**
     * 博主类型
     */
    private String bloggerType;
    /**
     * 博主等级
     */
    private Integer bloggerLevel;
    /**
     * 合作状态（1：已合作，0：未合作）
     */
    private Integer cooperationStatus;
    /**
     * 频道ID
     */
    private String channelId;
    /**
     * 频道名称
     */
    private String channelName;
    /**
     * 频道头像
     */
    private String channelAvatar;
    /**
     * 频道粉丝数
     */
    private Integer channelFollowerCount;
    /**
     * 频道视频总数
     */
    private Integer channelTotalVideos;
    /**
     * 频道获赞总数
     */
    private Integer channelTotalLikes;
    /**
     * 频道评论总数
     */
    private Integer channelTotalComments;
    /**
     * 频道分享总数
     */
    private Integer channelTotalShares;
    /**
     * 近30天频道GMV
     */
    @Column(value = "channel_video_gmv_30d")
    private Double channelVideoGmv30d;
    /**
     * 近90天频道GMV
     */
    @Column(value = "channel_video_gmv_90d")
    private Double channelVideoGmv90d;
    /**
     * 近180天频道GMV
     */
    @Column(value = "channel_video_gmv_180d")
    private Double channelVideoGmv180d;
    /**
     * 近365天频道GMV
     */
    @Column(value = "channel_video_gmv_365d")
    private Double channelVideoGmv365d;
    /**
     * 累计频道GMV
     */
    private Double channelTotalGmv;
    /**
     * 最后发布频道视频时间
     */
    private Date channelLastVideoUploadTime;
    /**
     * 最后活跃时间
     */
    private Date channelLastActiveTime;
    /**
     * 频道创建时间
     */
    private Date channelAccountCreateTime;
    /**
     * 频道是否认证
     */
    private Integer channelVerified;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private Integer delFlag;
    /**
     * 创建者
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;


}
