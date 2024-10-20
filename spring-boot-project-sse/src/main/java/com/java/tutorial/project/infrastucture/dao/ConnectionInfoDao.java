package com.java.tutorial.project.infrastucture.dao;

import com.java.tutorial.project.infrastucture.entity.ConnectionEntity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author meta
 */
@Repository
public interface ConnectionInfoDao extends MongoRepository<ConnectionEntity, String> {
    /**
     * 根据id查询连接
     * @param clientId
     * @return
     */
    List<ConnectionEntity> findByClientId(String clientId);


    @Query("{ 'lastContactTime': { $gt: ?0 } }")
    List<ConnectionEntity> findByLastContactTimeAfter(LocalDateTime now);


}
