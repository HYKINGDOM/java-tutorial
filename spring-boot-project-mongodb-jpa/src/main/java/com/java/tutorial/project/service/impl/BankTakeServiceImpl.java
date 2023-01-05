package com.java.tutorial.project.service.impl;


import com.java.tutorial.project.infrastucture.entity.BankEntity;
import com.java.tutorial.project.service.BankService;
import org.springframework.stereotype.Service;

@Service
public class BankTakeServiceImpl implements BankService {


    @Override
    public BankEntity updateByBankEntity(BankEntity bankEntity) {
        BankEntity bankEntity1 = BankEntity.builder().realKey("12345").build();

        return bankEntity1;
    }
}
