package com.java.coco.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户表
 *
 * @author meta
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserAccount {

    /**
     *
     */
    private Long id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 删除标识
     */
    private Integer delFlag;
    /**
     * 乐观锁
     */
    private Integer vision;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;

}
