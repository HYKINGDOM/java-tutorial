package com.java.patterns.ChainofResponsibility;

import org.apache.commons.lang3.StringUtils;

/**
 * @author HY
 */
public class ValidateHandler extends Handler<User> {
    @Override
    public void doHandler(User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassWord())) {
            System.out.println("用户名和密码不能为空");
            return;
        }
        if (null != next) {
            next.doHandler(user);
        }
    }
}