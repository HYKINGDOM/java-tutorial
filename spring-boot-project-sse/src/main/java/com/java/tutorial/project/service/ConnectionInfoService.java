package com.java.tutorial.project.service;

import com.java.tutorial.project.infrastucture.entity.ConnectionEntity;

import java.util.List;

/**
 * @author meta
 */
public interface ConnectionInfoService {
    /**
     * 添加连接
     *
     * @param connectionEntity
     * @return
     */
    int create(ConnectionEntity connectionEntity);

    /**
     * 获取连接信息
     * @param clientId
     * @return
     */
    ConnectionEntity getClient(String clientId);

    /**
     * 获取所有未过期的连接信息
     *
     * @return
     */
    List<ConnectionEntity> getAllConnectionByNoTimeOut();

    /**
     * 删除
     */
    int delete(List<String> clientIds);

    int deleteByClientId(String clientId);

    List<ConnectionEntity> listAll();

    void deleteAll();
}
