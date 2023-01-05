package com.java.tutorial.project.infrastucture.dao;


import com.java.tutorial.project.infrastucture.entity.BankEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author HY
 */
@Repository
public interface BankEntityRepository extends MongoRepository<BankEntity, String> {

}
