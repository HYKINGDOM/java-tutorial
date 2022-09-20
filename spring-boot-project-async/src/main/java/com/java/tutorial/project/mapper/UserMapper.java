package com.java.tutorial.project.mapper;



import com.java.tutorial.project.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> getUsers(List<Integer> ids);
}
