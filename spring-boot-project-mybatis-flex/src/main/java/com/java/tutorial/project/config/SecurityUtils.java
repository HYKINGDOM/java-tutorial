package com.java.tutorial.project.config;

import com.java.tutorial.project.domain.LoginUser;

/**
 * @author meta
 */
public class SecurityUtils {

    /**
     * 获取当前登录人员名称
     *
     * @return
     */
    public static String getDefaultLoginUserName() {
        return "admin";
    }

    public static LoginUser getLoginUser() {
        return new LoginUser();
    }
}
