package com.java.tutorial.project.dao;

import com.java.tutorial.project.common.entity.ConnectionInfo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author meta
 */
@Repository
public interface ConnectionInfoDao extends MongoRepository<ConnectionInfo, String> {
    /**
     * 根据id查询连接
     * @param clientId
     * @return
     */
    List<ConnectionInfo> findByClientId(String clientId);


    @Query("{ 'lastContactTime': { $gt: ?0 } }")
    List<ConnectionInfo> findByLastContactTimeAfter(LocalDateTime now);


}
