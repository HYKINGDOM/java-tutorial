package com.java.tutorial.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mybatisflex.annotation.Column;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Entity基类
 *
 * @author meta
 */
@Data
public class BaseEntity {

    /** 搜索值 */
    @JsonIgnore
    @Column(ignore = true)
    private String searchValue;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(onInsertValue = "now()")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(onUpdateValue = "now()", onInsertValue = "now()")
    private Date updateTime;

    /** 备注 */
    @Column(ignore = true)
    private String remark;

    /** 请求参数 */
    @Column(ignore = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;
}
