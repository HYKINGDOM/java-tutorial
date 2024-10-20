package com.java.tutorial.project.service.impl;

import com.java.tutorial.project.infrastucture.entity.ConnectionEntity;
import com.java.tutorial.project.infrastucture.dao.ConnectionInfoDao;
import com.java.tutorial.project.service.ConnectionInfoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author meta
 */
@Service
public class ConnectionInfoServiceImpl implements ConnectionInfoService {

    @Resource
    private ConnectionInfoDao connectionInfoDao;

    @Override
    public int create(ConnectionEntity connectionEntity) {
        connectionInfoDao.save(connectionEntity);
        return 0;
    }

    @Override
    public ConnectionEntity getClient(String clientId) {
        Optional<ConnectionEntity> optional = connectionInfoDao.findById(clientId);
        return optional.orElse(null);
    }

    @Override
    public List<ConnectionEntity> getAllConnectionByNoTimeOut() {
        return connectionInfoDao.findByLastContactTimeAfter(LocalDateTime.now());
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
    public List<ConnectionEntity> listAll() {
        return connectionInfoDao.findAll();
    }

    @Override
    public void deleteAll() {
        connectionInfoDao.deleteAll();
    }
}
