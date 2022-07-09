package com.java.util.javautil.patterns.strategypattern.funcmodel;

import static com.java.util.javautil.utils.ThreadLocalRandomUtils.generateRandomInteger;

public class ManagerAccountServiceImpl implements AccountService {

    private final static String MANAGER_ACCOUNT_TYPE = "manager_type";

    @Override
    public String getAccountName(String accountId) {
        return generateRandomInteger().toString();
    }

    @Override
    public boolean isAccountType(String accountType) {
        return MANAGER_ACCOUNT_TYPE.equalsIgnoreCase(accountType);
    }
}
