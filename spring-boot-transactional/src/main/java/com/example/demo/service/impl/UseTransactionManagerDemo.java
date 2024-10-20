package com.example.demo.service.impl;

import com.example.demo.domain.Account;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 编程式事务之-UseTransactionManagerDemo
 *
 * @author meta
 */
@Service
public class UseTransactionManagerDemo implements TransactionService {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public boolean isTransaction(String transactionType) {

        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            // ....  业务代码
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }

        return false;
    }

    @Override
    public String execute(Account account) {
        return null;
    }
}
