package com.example.demo.service.impl;

import com.example.demo.domain.Account;
import com.example.demo.exception.RollBackException;
import com.example.demo.infrastucture.dao.AccountRepository;
import com.example.demo.service.TransactionService;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * propagation_requires_new
 *
 * @author meta
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class PropagationRequiredNewTransactionServiceImpl implements TransactionService {

    private static final String PROPAGATION_REQUIRES_NEW = "PropagationRequiredNew";

    private final AccountRepository accountRepository;

    @Override
    public boolean isTransaction(String transactionType) {
        return PROPAGATION_REQUIRES_NEW.equalsIgnoreCase(transactionType);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String execute(Account account) {
        insertWithTxThrowException(account);
        return "操作成功";
    }

    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRES_NEW)
    public void insertWithTxThrowException(Account account) {
        accountRepository.save(account);
        throw new RollBackException("ROLL_BACK_MESSAGE");
    }
}
