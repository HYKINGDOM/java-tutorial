package com.java.data.infrastucture.dao;

import com.java.data.infrastucture.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntityDao extends MongoRepository<UserEntity, String> {
}
