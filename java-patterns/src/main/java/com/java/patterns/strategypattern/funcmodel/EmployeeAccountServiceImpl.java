package com.java.util.javautil.patterns.strategypattern.funcmodel;

import com.java.util.javautil.utils.ThreadLocalRandomUtils;

import static com.java.util.javautil.utils.ThreadLocalRandomUtils.generateRandomInteger;

public class EmployeeAccountServiceImpl implements AccountService {

    private final static String EMPLOYEE_ACCOUNT_TYPE = "manager_type";

    @Override
    public String getAccountName(String accountId) {
        return generateRandomInteger().toString();
    }

    @Override
    public boolean isAccountType(String accountType) {
        return EMPLOYEE_ACCOUNT_TYPE.equalsIgnoreCase(accountType);
    }
}
