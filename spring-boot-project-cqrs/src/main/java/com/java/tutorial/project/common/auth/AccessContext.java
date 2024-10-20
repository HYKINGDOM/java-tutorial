package com.java.tutorial.project.common.auth;

/**
 * 用户对象上下文, 用于处理当前操作的用户信息
 *
 * @author meta
 */
public final class AccessContext {

    private static final ThreadLocal<AccessUser> LOCAL = new ThreadLocal<>();

    public static AccessUser getAccessUser() {
        return LOCAL.get();
    }

    public static void setAccessUser(AccessUser user) {
        LOCAL.set(user);
    }

    public static void remove() {
        LOCAL.remove();
    }

}
