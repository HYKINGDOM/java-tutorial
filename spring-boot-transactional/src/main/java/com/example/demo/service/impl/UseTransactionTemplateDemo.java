package com.example.demo.service.impl;

import com.example.demo.domain.Account;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * 编程式事务之TransactionTemplate
 */
@Service
public class UseTransactionTemplateDemo implements TransactionService {


    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public boolean isTransaction(String transactionType) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {

                try {

                    // ....  业务代码
                } catch (Exception e){
                    //回滚
                    transactionStatus.setRollbackOnly();
                }

            }
        });


        return false;
    }

    @Override
    public String execute(Account account) {
        return null;
    }
}
