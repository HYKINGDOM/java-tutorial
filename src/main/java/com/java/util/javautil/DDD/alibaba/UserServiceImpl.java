package com.java.util.javautil.DDD.alibaba;

public class UserServiceImpl implements UserService{
    @Override
    public User save(User user) {
        System.out.println("user has been save");
        return user;
    }
}
