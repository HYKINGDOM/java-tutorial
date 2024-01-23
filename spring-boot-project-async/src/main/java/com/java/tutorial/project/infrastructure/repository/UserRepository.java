package com.java.tutorial.project.infrastructure.repository;

import com.java.tutorial.project.infrastructure.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers(List<Integer> ids);
}
