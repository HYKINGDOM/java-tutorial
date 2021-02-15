package com.java.util.javautil.DDD.alibaba;

import javax.validation.constraints.NotNull;

/**
 * @author bingbing
 */
public class User {

    UserId userId;

    PhoneNumber phone;

    public User register(@NotNull UserId userId, @NotNull PhoneNumber phone) {

        UserService userService = new UserServiceImpl();

        // 最后创建用户，落盘，然后返回，这部分代码实际上也能用Builder解决
        User user = new User();
        user.userId = userId;
        user.phone = phone;

        return userService.save(user);
    }
}


