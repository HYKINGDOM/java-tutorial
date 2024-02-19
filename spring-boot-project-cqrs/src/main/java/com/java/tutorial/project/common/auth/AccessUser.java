package com.java.tutorial.project.common.auth;

/**
 * 当前访问用户
 */
public final class AccessUser {

    private Long id;

    public AccessUser() {
    }

    public AccessUser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
