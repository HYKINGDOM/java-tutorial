package com.java.tutorial.project.domain;

import lombok.Data;

import java.util.Set;

/**
 * 登录用户身份权限
 *
 * @author meta
 */
@Data
public class LoginUser {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;
    /**
     * 权限列表
     */
    private Set<String> permissions;

}
