package com.java.data.service.impl;

import com.java.data.domain.User;
import com.java.data.infrastucture.dao.UserEntityRepository;
import com.java.data.infrastucture.entity.UserEntity;
import com.java.data.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author HY
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;

    /**
     * 创建对象
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        userEntityRepository.saveUser(user);
    }

    /**
     * 根据用户名查询对象
     *
     * @param userName
     * @return
     */
    @Override
    public UserEntity findUserByUserName(String userName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        return userEntityRepository.findUserByUserName(userName);
    }

    /**
     * 更新对象
     *
     * @param userEntity
     */
    @Override
    public long updateUser(UserEntity userEntity) {
        return 0;
    }

    /**
     * 删除对象
     *
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
    }
}
