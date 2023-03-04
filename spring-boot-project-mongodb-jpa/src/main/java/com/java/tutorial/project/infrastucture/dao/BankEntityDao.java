package com.java.tutorial.project.infrastucture.dao;


import com.java.tutorial.project.infrastucture.entity.BankEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author HY
 */
public interface BankEntityDao extends MongoRepository<BankEntity, String> {

}
