package com.java.patterns.strategypattern.funcmodel;

import cn.hutool.core.util.RandomUtil;

public class ManagerAccountServiceImpl implements AccountService {

    private final static String MANAGER_ACCOUNT_TYPE = "manager_type";

    @Override
    public String getAccountName(String accountId) {
        return RandomUtil.randomString(12);
    }

    @Override
    public boolean isAccountType(String accountType) {
        return MANAGER_ACCOUNT_TYPE.equalsIgnoreCase(accountType);
    }
}
