package com.java.tutorial.project.mapper;



import com.java.tutorial.project.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers(List<Integer> ids);
}
