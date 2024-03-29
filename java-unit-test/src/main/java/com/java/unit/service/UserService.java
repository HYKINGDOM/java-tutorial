package com.java.unit.service;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int getUserCount() {
        return userDao.getCount();
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

}
