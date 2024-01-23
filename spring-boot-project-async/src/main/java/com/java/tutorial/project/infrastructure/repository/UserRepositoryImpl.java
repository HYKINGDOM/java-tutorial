package com.java.tutorial.project.infrastructure.repository;

import com.java.tutorial.project.infrastructure.domain.User;
import com.java.tutorial.project.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author meta
 */
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public List<User> getUsers(List<Integer> ids) {
        return null;
    }
}
