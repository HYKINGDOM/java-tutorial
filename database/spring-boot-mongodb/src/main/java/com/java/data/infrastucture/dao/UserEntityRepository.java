package com.java.data.infrastucture.dao;

import com.java.data.domain.User;
import com.java.data.infrastucture.entity.UserEntity;

public interface UserEntityRepository {

    void saveUser(User user);

    UserEntity findUserByUserName(String userName);

    long updateUser(UserEntity userEntity);

    void deleteUserById(Long id);
}
