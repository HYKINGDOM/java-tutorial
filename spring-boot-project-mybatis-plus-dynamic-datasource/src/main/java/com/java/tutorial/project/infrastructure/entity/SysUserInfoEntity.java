package com.java.tutorial.project.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户表;
 *
 * @author meta
 */
@Data
@TableName("sys_user_info")
public class SysUserInfoEntity {

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 昵称
     */
    private String nikeName;
    /**
     * 个性签名
     */
    private String userSignature;
    /**
     * 身份证号
     */
    private String idCardNo;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 出生日期
     */
    private Date birth;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 体重
     */
    private String weight;
    /**
     * 身高
     */
    private String height;
    /**
     * 名族
     */
    private String nation;
    /**
     * 政治面貌
     */
    private String political;
    /**
     * 婚姻状况
     */
    private String marital;
    /**
     * 籍贯（省）
     */
    private String domicilePlaceProvince;
    /**
     * 籍贯（市）
     */
    private String domicilePlaceCity;
    /**
     * 户籍地址
     */
    private String domicilePlaceAddress;
    /**
     * 注册IP
     */
    private String registerIp;
    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 最后一次登录IP
     */
    private String lastLoginIp;
    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;
    /**
     * 删除标识
     */
    private Integer delFlag;
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
