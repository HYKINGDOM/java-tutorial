package com.java.data.service;


import com.java.data.domain.User;
import com.java.data.infrastucture.entity.UserEntity;

public interface UserService {

    void saveUser(User user);

    UserEntity findUserByUserName(String userName);

    long updateUser(UserEntity userEntity);

    void deleteUserById(Long id);
}
