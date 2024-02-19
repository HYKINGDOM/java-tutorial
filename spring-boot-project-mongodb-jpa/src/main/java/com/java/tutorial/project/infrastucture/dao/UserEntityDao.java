package com.java.tutorial.project.infrastucture.dao;

import com.java.tutorial.project.infrastucture.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntityDao extends MongoRepository<UserEntity, String> {
}
