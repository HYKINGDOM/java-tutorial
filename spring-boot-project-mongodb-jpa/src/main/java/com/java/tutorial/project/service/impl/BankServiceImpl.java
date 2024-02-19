package com.java.tutorial.project.service.impl;

import com.java.tutorial.project.infrastucture.entity.BankEntity;
import com.java.tutorial.project.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BankEntity updateByBankEntity(BankEntity bankEntity) {
        BankEntity bankEntity1 = BankEntity.builder().realKey("qwerrd").build();

        return bankEntity1;
    }
}
