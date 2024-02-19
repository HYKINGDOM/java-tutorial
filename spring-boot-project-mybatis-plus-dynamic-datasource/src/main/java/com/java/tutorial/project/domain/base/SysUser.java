package com.java.tutorial.project.domain.base;

import lombok.Data;

import java.util.Date;

/**
 * 用户表
 *
 * @author hy
 */
@Data
public class SysUser {

    /**
     *
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
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
     * 用户详情
     */
    private SysUserInfo sysUserInfo;
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
    private Date createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    private Date updatedTime;

}
