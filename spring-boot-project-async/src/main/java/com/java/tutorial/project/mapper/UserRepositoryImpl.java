package com.java.tutorial.project.mapper;

import com.java.tutorial.project.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Override
    public List<User> getUsers(List<Integer> ids) {
        return null;
    }
}
