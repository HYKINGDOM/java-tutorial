package com.java.tutorial.project.service.impl;

import com.java.tutorial.project.common.entity.ConnectionInfo;
import com.java.tutorial.project.dao.ConnectionInfoDao;
import com.java.tutorial.project.service.ConnectionInfoService;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ConnectionInfoServiceImpl implements ConnectionInfoService {
    @Resource
    private ConnectionInfoDao connectionInfoDao;

    @Override
    public int create(ConnectionInfo connectionInfo) {
        connectionInfoDao.save(connectionInfo);
        return 0;
    }

    @Override
    public ConnectionInfo getClient(String clientId) {
        Optional<ConnectionInfo> optional = connectionInfoDao.findById(clientId);
        return optional.orElse(null);
    }

    @Override
    public int delete(List<String> clientIds) {

        return 0;
    }

    @Override
    public int deleteByClientId(String clientId) {
        connectionInfoDao.deleteById(clientId);
        return 1;
    }

    @Override
    public List<ConnectionInfo> listAll() {
        return connectionInfoDao.findAll();
    }

    @Override
    public void deleteAll() {
        connectionInfoDao.deleteAll();
    }
}
