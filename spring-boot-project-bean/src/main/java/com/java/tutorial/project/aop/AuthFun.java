package com.java.tutorial.project.aop;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

public class AuthFun {


    /**
     * 判断角色是否具有接口权限
     *
     * @return {boolean}
     */
    public boolean permissionAll() {
        //TODO
    }

    /**
     * 判断角色是否具有接口权限
     *
     * @param permission 权限编号,对应菜单的MENU_CODE
     * @return {boolean}
     */
    public boolean hasPermission(String permission) {
        //TODO
    }

    /**
     * 放行所有请求
     *
     * @return {boolean}
     */
    public boolean permitAll() {
        return true;
    }

    /**
     * 只有超管角色才可访问
     *
     * @return {boolean}
     */
    public boolean denyAll() {
        return hasRole(RoleConstant.ADMIN);
    }

    /**
     * 是否已授权
     *
     * @return {boolean}
     */
    public boolean hasAuth() {
        if(Func.isEmpty(AuthUtil.getUser())){
            // TODO 返回异常提醒
        }else{
            return true;
        }
    }

    /**
     * 是否有时间授权
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return {boolean}
     */
    public boolean hasTimeAuth(Integer start, Integer end) {
        Integer hour = DateUtil.hour();
        return hour >= start && hour <= end;
    }

    /**
     * 判断是否有该角色权限
     *
     * @param role 单角色
     * @return {boolean}
     */
    public boolean hasRole(String role) {
        return hasAnyRole(role);
    }

    /**
     * 判断是否具有所有角色权限
     *
     * @param role 角色集合
     * @return {boolean}
     */
    public boolean hasAllRole(String... role) {
        for (String r : role) {
            if (!hasRole(r)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否有该角色权限
     *
     * @param role 角色集合
     * @return {boolean}
     */
    public boolean hasAnyRole(String... role) {
        //获取当前登录用户
        BladeUser user = AuthUtil.getUser();
        if (user == null) {
            return false;
        }
        String userRole = user.getRoleName();
        if (StrUtil.isBlank(userRole)) {
            return false;
        }
        String[] roles = Func.toStrArray(userRole);
        for (String r : role) {
            if (CollUtil.contains(roles, r)) {
                return true;
            }
        }
        return false;
    }

}

