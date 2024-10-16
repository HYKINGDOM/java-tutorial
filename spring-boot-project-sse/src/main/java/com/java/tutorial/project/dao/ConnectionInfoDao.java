package com.java.tutorial.project.dao;

import com.java.tutorial.project.common.entity.ConnectionInfo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author meta
 */
public interface ConnectionInfoDao extends MongoRepository<ConnectionInfo, String> {
    /**
     * 根据id查询连接
     * @param clientId
     * @return
     */
    List<ConnectionInfo> findByClientId(String clientId);

}
