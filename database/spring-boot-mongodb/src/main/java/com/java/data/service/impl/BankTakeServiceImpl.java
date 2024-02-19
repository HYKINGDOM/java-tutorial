package com.java.data.service.impl;

import com.java.data.infrastucture.entity.BankEntity;
import com.java.data.service.BankService;
import org.springframework.stereotype.Service;

@Service
public class BankTakeServiceImpl implements BankService {

    @Override
    public BankEntity updateByBankEntity(BankEntity bankEntity) {
        BankEntity bankEntity1 = BankEntity.builder().realKey("12345").build();

        return bankEntity1;
    }
}
