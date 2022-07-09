package com.java.util.javautil.function.supplier;

import java.util.Set;

public class LazyUser {

    // 用户 id
    private Long uid;
    // 用户的部门，为了保持示例简单，这里就用普通的字符串
    // 需要远程调用 通讯录系统 获得
    private Lazy<String> department;
    // 用户的主管，为了保持示例简单，这里就用一个 id 表示
    // 需要远程调用 通讯录系统 获得
    private Lazy<Long> supervisor;
    // 用户所含有的权限
    // 需要远程调用 权限系统 获得
    private Lazy<Set<String>> permission;


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(Lazy<String> department) {
        this.department = department;
    }

    public Long getSupervisor() {
        return supervisor.get();
    }

    public void setSupervisor(Lazy<Long> supervisor) {
        this.supervisor = supervisor;
    }

    public Set<String> getPermission() {
        return permission.get();
    }

    public void setPermission(Lazy<Set<String>> permission) {
        this.permission = permission;
    }
}
