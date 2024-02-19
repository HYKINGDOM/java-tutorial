package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.User;
import com.java.tutorial.project.infrastucture.entity.UserEntity;

public interface UserService {

    void saveUser(User user);

    UserEntity findUserByUserName(String userName);

    long updateUser(UserEntity userEntity);

    void deleteUserById(Long id);
}
