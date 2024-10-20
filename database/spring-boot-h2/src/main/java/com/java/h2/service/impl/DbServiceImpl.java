package com.java.h2.service.impl;

import com.java.h2.domain.Account;
import com.java.h2.infrastucture.dao.AccountRepository;
import com.java.h2.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * propagation_required
 *
 * @author meta
 */
@Service
@RequiredArgsConstructor
public class DbServiceImpl implements DbService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String execute(Account account) {
        accountRepository.save(account);
        return "操作成功";
    }

}
