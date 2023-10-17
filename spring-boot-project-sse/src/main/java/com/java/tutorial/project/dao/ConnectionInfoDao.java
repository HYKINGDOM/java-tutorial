package com.java.tutorial.project.dao;

import com.java.tutorial.project.common.entity.ConnectionInfo;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConnectionInfoDao extends MongoRepository<ConnectionInfo,String> {
    //根据id查询连接
    List<ConnectionInfo> findByclientId(String clientId);


}
