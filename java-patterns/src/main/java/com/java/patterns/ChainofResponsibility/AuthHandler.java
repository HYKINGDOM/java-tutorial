package com.java.patterns.ChainofResponsibility;


public class AuthHandler extends Handler<User> {
    @Override
    public void doHandler(User user) {
        if (!"管理员".equals(user.getRoleName())) {
            System.out.println("您不是管理员，没有操作权限");
            return;
        }
        if (null != next) {
            next.doHandler(user);
        }
    }
}