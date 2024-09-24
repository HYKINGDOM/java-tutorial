package com.java.tutorial.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * 达人信息对象
 * @author meta
 */
@Getter
@Setter
@Entity
@Table(name = "creator_info")
public class CreatorInfo {

    /**
     * 达人ID
     */
    @Id
    private String creatorId;

    /**
     * 达人名称
     */
    private String creatorName;

    /**
     * 达人昵称
     */
    private String creatorNickname;

    /**
     * 达人头像
     */
    private String creatorPortrait;

    /**
     * MCN签约
     */
    private String mcnContract;

    /**
     * 达人标签
     */
    private String creatorCategories;

    /**
     * 带货方式
     */
    private String ecommerceType;

    /**
     * 达人认证
     */
    private String creatorVerification;

    /**
     * 达人简介
     */
    private String description;

    /**
     * 主页地址
     */
    private String homepage;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 达人国家
     */
    private String country;

    /**
     * 机构名称
     */
    private String capName;

    /**
     * 添加日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date addTime;

    /**
     * 来源平台
     */
    private String sourcePlatform;

    /**
     * 粉丝数
     */
    private Long fansNum;

    /**
     * 累计关注数
     */
    private Long followers;

    /**
     * 累计点赞数
     */
    private Long likes;

    /**
     * 累计视频数
     */
    private Long videos;

    /**
     * 累计带货直播数
     */
    @Column(name = "livestreamings")
    private Long livestreamings;

    /**
     * 视频GPM-最小
     */
    private Double videoGpmMin;

    /**
     * 视频GPM-最大
     */
    private Double videoGpmMax;

    /**
     * 直播GPM-最小
     */
    private Long liveGpmMin;

    /**
     * 直播GPM-最大
     */
    private Long liveGpmMax;

    /**
     * 本国排名
     */
    private Long nationalRank;

    /**
     * 累计关联店铺数
     */
    private Long relatedShops;

    /**
     * 累计带货商品数
     */
    private Long promotedProducts;

    /**
     * 达人TOP1带货类目名称
     */
    @Column(name = "product_categories_top1")
    private String productCategoriesTop1;

    /**
     * 达人TOP1带货类目名称
     */
    @Column(name = "product_categories_top1_en")
    private String productCategoriesTop1En;

    /**
     * 达人TOP1带货类目占比
     */
    @Column(name = "product_categories_proportion_top1")
    private Long productCategoriesProportionTop1;

    /**
     * 达人TOP1带货类目名称
     */
    @Column(name = "product_categories_top2")
    private String productCategoriesTop2;

    /**
     * 达人TOP1带货类目名称
     */
    @Column(name = "product_categories_top2_en")
    private String productCategoriesTop2En;

    /**
     * 达人TOP1带货类目占比
     */
    @Column(name = "product_categories_proportion_top2")
    private Long productCategoriesProportionTop2;

    /**
     * 达人TOP1带货类目名称
     */
    @Column(name = "product_categories_top3")
    private String productCategoriesTop3;

    /**
     * 达人TOP1带货类目名称
     */
    @Column(name = "product_categories_top3_en")
    private String productCategoriesTop3En;

    /**
     * 达人TOP1带货类目占比
     */
    @Column(name = "product_categories_proportion_top3")
    private Long productCategoriesProportionTop3;

    /**
     * 达人TOP1带货类目名称
     */
    @Column(name = "product_categories_top4")
    private String productCategoriesTop4;

    /**
     * 达人TOP1带货类目名称
     */
    @Column(name = "product_categories_top4_en")
    private String productCategoriesTop4En;

    /**
     * 达人TOP1带货类目占比
     */
    @Column(name = "product_categories_proportion_top4")
    private Long productCategoriesProportionTop4;

    /**
     * 达人TOP1带货类目名称
     */
    @Column(name = "product_categories_top5")
    private String productCategoriesTop5;

    /**
     * 达人TOP1带货类目名称
     */
    @Column(name = "product_categories_top5_en")
    private String productCategoriesTop5En;

    /**
     * 达人TOP1带货类目占比
     */
    @Column(name = "product_categories_proportion_top5")
    private Long productCategoriesProportionTop5;

    /**
     * 粉丝18-24年龄占比
     */
    @Column(name = "fans_18_24_percent")
    private Long fans1824Percent;

    /**
     * 粉丝25-34年龄占比
     */
    @Column(name = "fans_25_34_percent")
    private Long fans2534Percent;

    /**
     * 粉丝35+年龄占比
     */
    @Column(name = "fans_35_above_percent")
    private Long fans35AbovePercent;

    /**
     * 女性占比
     */
    private Long femalePercent;

    /**
     * 男性占比
     */
    private Long malePercent;

    /**
     * 近30条视频播放数
     */
    @Column(name = "recent_30_videos_views")
    private Long recent30VideosViews;

    /**
     * 近30条视频点赞数
     */
    @Column(name = "recent_30_videos_likes")
    private Long recent30VideosLikes;

    /**
     * 近30条视频评论数
     */
    @Column(name = "recent_30_videos_comments")
    private Long recent30VideosComments;

    /**
     * 近30条视频分享数
     */
    @Column(name = "recent_30_videos_shares")
    private Long recent30VideosShares;

    /**
     * 近30天GMV
     */
    @Column(name = "recent_30_days_gmv")
    private Long recent30DaysGmv;

    /**
     * 近7日粉丝增量
     */
    @Column(name = "videoslikes_gained_7days")
    private Long videoslikesGained7days;

    /**
     * 近7日带货直播数
     */
    @Column(name = "ecommerce_lives_7days")
    private Long ecommerceLives7days;

    /**
     * 近7日视频数
     */
    @Column(name = "videos_7days")
    private Long videos7days;

    /**
     * 近7日视频点赞增量
     */
    @Column(name = "followers_gained_7days")
    private Long followersGained7days;

    /**
     * 近7日视频IPM
     */
    @Column(name = "video_ipm_7days")
    private Long videoIpm7days;

    /**
     * 近28天带货视频数
     */
    @Column(name = "ecommerce_videos_28days")
    private Long ecommerceVideos28days;

    /**
     * 近28天视频带货商品数
     */
    @Column(name = "products_promoted_in_videos_28days")
    private Long productsPromotedInVideos28days;

    /**
     * 粉丝主要年龄(18-24,25-34,35+)
     */
    @Column(name = "fans_main_age")
    private String fansMainAge;

    /**
     * 粉丝主要性别(女,男)
     */
    @Column(name = "fans_main_sex")
    private String fansMainSex;

    /**
     * 近28天视频带货总GMV
     */
    @Column(name = "video_gmv_28days")
    private Double videoGmv28days;

    /**
     * 近28天直播带货总GMV
     */
    @Column(name = "live_gmv_28days")
    private Double liveGmv28days;

    /**
     * 近28天带货总GMV
     */
    @Column(name = "total_gmv_28days")
    private Double totalGmv28days;

    /**
     * 最大销量
     */
    @Column(name = "product_ecommerce_orders_max")
    private Double productEcommerceOrdersMax;

    /**
     * 爬取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crawTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 累计带货总GMV
     */
    @Column(name = "total_gmv_all")
    private Double totalGmvAll;

    /**
     * 累计带货视频数
     */
    @Column(name = "ecommerce_videos")
    private Long ecommerceVideos;

    /**
     * 近60天带货视频数
     */
    @Column(name = "ecommerce_videos_60days")
    private Long ecommerceVideos60days;

    /**
     * 近60天视频带货GMV
     */
    @Column(name = "recent_60_days_gmv")
    private Double recent60DaysGmv;

    /**
     * 近90天带货视频数
     */
    @Column(name = "ecommerce_videos_90days")
    private Long ecommerceVideos90days;

    /**
     * 近90天视频带货GMV
     */
    @Column(name = "recent_90_days_gmv")
    private Double recent90DaysGmv;

    /**
     * instagram账号名称
     */
    @Column(name = "instagram_username")
    private String instagramUsername;

    /**
     * instagram主页
     */
    @Column(name = "instagram_link")
    private String instagramLink;

    /**
     * youtube主页
     */
    @Column(name = "youtube_link")
    private String youtubeLink;

    /**
     * 所属youtube频道
     */
    @Column(name = "youtube_channel_title")
    private String youtubeChannelTitle;

    /**
     * 是否绑定(已绑定)
     */
    @Column(name = "bound_status")
    private String boundStatus;

    /**
     * 是否分佣达人:0不分佣，1分佣
     */
    @Column(name = "is_commission_daren")
    private Integer isCommissionDaren;
    /**
     * 近7天视频带货总GMV
     */
    @Column(name = "video_gmv_7days")
    private Double videoGmv7days;
    /**
     * 近7天带货总GMV
     */
    @Column(name = "total_gmv_7days")
    private Double totalGmv7days;
    /**
     * 近14天视频带货总GMV
     */
    @Column(name = "video_gmv_14days")
    private Double videoGmv14days;
    /**
     * 近14天带货总GMV
     */
    @Column(name = "total_gmv_14days")
    private Double totalGmv14days;
    /**
     * 近7天视频播放中位数
     */
    @Column(name = "median_watch_pv_video_7d")
    private Integer medianWatchPvVideo7d;
    /**
     * 近14天视频播放中位数
     */
    @Column(name = "median_watch_pv_video_14d")
    private Integer medianWatchPvVideo14d;
    /**
     * 近28天视频播放中位数
     */
    @Column(name = "median_watch_pv_video_28d")
    private Integer medianWatchPvVideo28d;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreatorInfo that = (CreatorInfo)o;
        return Objects.equals(creatorId, that.creatorId) && Objects.equals(creatorName,
            that.creatorName) && Objects.equals(creatorNickname, that.creatorNickname) && Objects.equals(
            creatorPortrait, that.creatorPortrait) && Objects.equals(mcnContract, that.mcnContract) && Objects.equals(
            creatorCategories, that.creatorCategories) && Objects.equals(ecommerceType,
            that.ecommerceType) && Objects.equals(creatorVerification, that.creatorVerification) && Objects.equals(
            description, that.description) && Objects.equals(homepage, that.homepage) && Objects.equals(email,
            that.email) && Objects.equals(country, that.country) && Objects.equals(capName,
            that.capName) && Objects.equals(addTime, that.addTime) && Objects.equals(sourcePlatform,
            that.sourcePlatform) && Objects.equals(fansNum, that.fansNum) && Objects.equals(followers,
            that.followers) && Objects.equals(likes, that.likes) && Objects.equals(videos,
            that.videos) && Objects.equals(livestreamings, that.livestreamings) && Objects.equals(videoGpmMin,
            that.videoGpmMin) && Objects.equals(videoGpmMax, that.videoGpmMax) && Objects.equals(liveGpmMin,
            that.liveGpmMin) && Objects.equals(liveGpmMax, that.liveGpmMax) && Objects.equals(nationalRank,
            that.nationalRank) && Objects.equals(relatedShops, that.relatedShops) && Objects.equals(promotedProducts,
            that.promotedProducts) && Objects.equals(productCategoriesTop1,
            that.productCategoriesTop1) && Objects.equals(productCategoriesTop1En,
            that.productCategoriesTop1En) && Objects.equals(productCategoriesProportionTop1,
            that.productCategoriesProportionTop1) && Objects.equals(productCategoriesTop2,
            that.productCategoriesTop2) && Objects.equals(productCategoriesTop2En,
            that.productCategoriesTop2En) && Objects.equals(productCategoriesProportionTop2,
            that.productCategoriesProportionTop2) && Objects.equals(productCategoriesTop3,
            that.productCategoriesTop3) && Objects.equals(productCategoriesTop3En,
            that.productCategoriesTop3En) && Objects.equals(productCategoriesProportionTop3,
            that.productCategoriesProportionTop3) && Objects.equals(productCategoriesTop4,
            that.productCategoriesTop4) && Objects.equals(productCategoriesTop4En,
            that.productCategoriesTop4En) && Objects.equals(productCategoriesProportionTop4,
            that.productCategoriesProportionTop4) && Objects.equals(productCategoriesTop5,
            that.productCategoriesTop5) && Objects.equals(productCategoriesTop5En,
            that.productCategoriesTop5En) && Objects.equals(productCategoriesProportionTop5,
            that.productCategoriesProportionTop5) && Objects.equals(fans1824Percent,
            that.fans1824Percent) && Objects.equals(fans2534Percent, that.fans2534Percent) && Objects.equals(
            fans35AbovePercent, that.fans35AbovePercent) && Objects.equals(femalePercent,
            that.femalePercent) && Objects.equals(malePercent, that.malePercent) && Objects.equals(recent30VideosViews,
            that.recent30VideosViews) && Objects.equals(recent30VideosLikes,
            that.recent30VideosLikes) && Objects.equals(recent30VideosComments,
            that.recent30VideosComments) && Objects.equals(recent30VideosShares,
            that.recent30VideosShares) && Objects.equals(recent30DaysGmv, that.recent30DaysGmv) && Objects.equals(
            videoslikesGained7days, that.videoslikesGained7days) && Objects.equals(ecommerceLives7days,
            that.ecommerceLives7days) && Objects.equals(videos7days, that.videos7days) && Objects.equals(
            followersGained7days, that.followersGained7days) && Objects.equals(videoIpm7days,
            that.videoIpm7days) && Objects.equals(ecommerceVideos28days, that.ecommerceVideos28days) && Objects.equals(
            productsPromotedInVideos28days, that.productsPromotedInVideos28days) && Objects.equals(fansMainAge,
            that.fansMainAge) && Objects.equals(fansMainSex, that.fansMainSex) && Objects.equals(videoGmv28days,
            that.videoGmv28days) && Objects.equals(liveGmv28days, that.liveGmv28days) && Objects.equals(totalGmv28days,
            that.totalGmv28days) && Objects.equals(productEcommerceOrdersMax,
            that.productEcommerceOrdersMax) && Objects.equals(crawTime, that.crawTime) && Objects.equals(updateTime,
            that.updateTime) && Objects.equals(totalGmvAll, that.totalGmvAll) && Objects.equals(ecommerceVideos,
            that.ecommerceVideos) && Objects.equals(ecommerceVideos60days,
            that.ecommerceVideos60days) && Objects.equals(recent60DaysGmv, that.recent60DaysGmv) && Objects.equals(
            ecommerceVideos90days, that.ecommerceVideos90days) && Objects.equals(recent90DaysGmv,
            that.recent90DaysGmv) && Objects.equals(instagramUsername, that.instagramUsername) && Objects.equals(
            instagramLink, that.instagramLink) && Objects.equals(youtubeLink, that.youtubeLink) && Objects.equals(
            youtubeChannelTitle, that.youtubeChannelTitle) && Objects.equals(boundStatus,
            that.boundStatus) && Objects.equals(isCommissionDaren, that.isCommissionDaren) && Objects.equals(
            videoGmv7days, that.videoGmv7days) && Objects.equals(totalGmv7days, that.totalGmv7days) && Objects.equals(
            videoGmv14days, that.videoGmv14days) && Objects.equals(totalGmv14days,
            that.totalGmv14days) && Objects.equals(medianWatchPvVideo7d, that.medianWatchPvVideo7d) && Objects.equals(
            medianWatchPvVideo14d, that.medianWatchPvVideo14d) && Objects.equals(medianWatchPvVideo28d,
            that.medianWatchPvVideo28d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creatorId, creatorName, creatorNickname, creatorPortrait, mcnContract, creatorCategories,
            ecommerceType, creatorVerification, description, homepage, email, country, capName, addTime, sourcePlatform,
            fansNum, followers, likes, videos, livestreamings, videoGpmMin, videoGpmMax, liveGpmMin, liveGpmMax,
            nationalRank, relatedShops, promotedProducts, productCategoriesTop1, productCategoriesTop1En,
            productCategoriesProportionTop1, productCategoriesTop2, productCategoriesTop2En,
            productCategoriesProportionTop2, productCategoriesTop3, productCategoriesTop3En,
            productCategoriesProportionTop3, productCategoriesTop4, productCategoriesTop4En,
            productCategoriesProportionTop4, productCategoriesTop5, productCategoriesTop5En,
            productCategoriesProportionTop5, fans1824Percent, fans2534Percent, fans35AbovePercent, femalePercent,
            malePercent, recent30VideosViews, recent30VideosLikes, recent30VideosComments, recent30VideosShares,
            recent30DaysGmv, videoslikesGained7days, ecommerceLives7days, videos7days, followersGained7days,
            videoIpm7days, ecommerceVideos28days, productsPromotedInVideos28days, fansMainAge, fansMainSex,
            videoGmv28days, liveGmv28days, totalGmv28days, productEcommerceOrdersMax, crawTime, updateTime, totalGmvAll,
            ecommerceVideos, ecommerceVideos60days, recent60DaysGmv, ecommerceVideos90days, recent90DaysGmv,
            instagramUsername, instagramLink, youtubeLink, youtubeChannelTitle, boundStatus, isCommissionDaren,
            videoGmv7days, totalGmv7days, videoGmv14days, totalGmv14days, medianWatchPvVideo7d, medianWatchPvVideo14d,
            medianWatchPvVideo28d);
    }
}
