package com.java.tutorial.project.service;

import com.java.tutorial.project.common.entity.ConnectionInfo;

import java.util.List;

/**
 * @author meta
 */
public interface ConnectionInfoService {
    /**
     * 添加连接
     *
     * @param connectionInfo
     * @return
     */
    int create(ConnectionInfo connectionInfo);

    ConnectionInfo getClient(String clientId);

    /**
     * 删除
     */
    int delete(List<String> clientIds);

    int deleteByClientId(String clientId);

    List<ConnectionInfo> listAll();

    void deleteAll();
}
