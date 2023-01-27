package com.java.tutorial.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 千川广告主消耗数据表对应实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DspQcAdvertiserCost extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 代理商 ID
     */
    private Long agentId;

    /**
     * 代理商名称
     */
    private String agentName;

    /**
     * 广告主ID
     */
    private Long advertiserId;

    /**
     * 广告主名称
     */
    private String advertiserName;

    /**
     * 消耗
     */
    private BigDecimal cost;

    /**
     * 展示次数
     */
    private Integer showCnt;

    /**
     * 点击率(单位:%)
     */
    private BigDecimal ctr;

    /**
     * 点击次数(click_cnt)
     */
    private Integer click;

    /**
     * 成交订单数
     */
    private Integer payOrderCount;

    /**
     * 平均千次展示价格
     */
    private BigDecimal cpm;

    /**
     * 下单成交金额
     */
    private BigDecimal createOrderAmount;

    /**
     * 新增粉丝数
     */
    private Integer dyFollow;

    /**
     * 0:ALL:不限 1:VIDEO_PROM_GOODS：短视频带货 2:LIVE_PROM_GOODS：直播间带货
     */
    private Integer marketingGoal;

    /**
     * 下单roi
     */
    private BigDecimal createOrderRoi;

    /**
     * 下单订单数
     */
    private Integer createOrderCount;

    /**
     * 成交订单金额
     */
    private BigDecimal payOrderAmount;

    /**
     * 成交roi
     */
    private BigDecimal payOrderRoi;

    /**
     * 是否同步
     */
    private Integer isSync;

    /**
     * 删除标识
     */
    private String delFlag;

    /**
     * 归属年
     */
    private String year;

    /**
     * 归属月
     */
    private Integer month;

    /**
     * 归属天
     */
    private Integer day;

    /**
     * 归属年月日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    /**
     * 是否同步实时数据
     */
    private Integer isSycnActual;

    /**
     * 归属销售id
     */
    private Long salerUserId;

    /**
     * 归属销售姓名
     */
    private String salerUserName;

    /**
     * 销售部门id
     */
    private Long salerDeptId;

    /**
     * 销售部门名称
     */
    private String salerDeptName;

    /**
     * 归属运营id
     */
    private Long operUserId;

    /**
     * 归属运营姓名
     */
    private String operUserName;

    /**
     * 归属运营部门id
     */
    private Long operDeptId;

    /**
     * 归属运营部门名称
     */
    private String operDeptName;

    /**
     * 租户id
     */
    private String tenantId;

}

