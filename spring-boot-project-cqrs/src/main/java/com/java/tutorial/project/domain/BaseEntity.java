package com.java.tutorial.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.tutorial.project.common.constant.DateFormatConstant;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础类
 *
 * @author HY
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 删除标识
     */
    private String delFlag;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateFormatConstant.DEFAULT_DATE, timezone = DateFormatConstant.TIMEZONE)
    @DateTimeFormat(pattern = DateFormatConstant.DEFAULT_DATE)
    private Date createTime;

    /**
     * 更新者
     */
    private String updateUser;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = DateFormatConstant.DEFAULT_DATE, timezone = DateFormatConstant.TIMEZONE)
    @DateTimeFormat(pattern = DateFormatConstant.DEFAULT_DATE)
    private Date updateTime;

}

