package com.java.tutorial.project.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户表
 *
 * @author meta
 */
@Data
@TableName("sys_user")
public class SysUserEntity {

    /**
     *
     */
    @TableId
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
     * 删除标识
     */
    private Integer delFlag;
    /**
     * 乐观锁
     */
    private Integer version;
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
