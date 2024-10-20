package com.java.data.infrastucture.dao;

import com.java.data.infrastucture.entity.BankEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author meta
 */
@Repository
public interface BankEntityRepository extends MongoRepository<BankEntity, String> {

}
