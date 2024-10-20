package com.example.demo.service.impl;

import com.example.demo.domain.Account;
import com.example.demo.exception.RollBackException;
import com.example.demo.infrastucture.dao.AccountRepository;
import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * propagation_required
 *
 * @author meta
 */
@Service
@RequiredArgsConstructor
public class PropagationRequiredTransactionServiceImpl implements TransactionService {

    private static final String PROPAGATION_REQUIRED = "PropagationRequired";

    private final AccountRepository accountRepository;

    @Override
    public boolean isTransaction(String transactionType) {
        return PROPAGATION_REQUIRED.equalsIgnoreCase(transactionType);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String execute(Account account) {
        insertWithTxThrowException(account);
        return "操作成功";
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void insertWithTxThrowException(Account account) {
        accountRepository.save(account);
        throw new RollBackException("ROLL_BACK_MESSAGE");
    }
}
