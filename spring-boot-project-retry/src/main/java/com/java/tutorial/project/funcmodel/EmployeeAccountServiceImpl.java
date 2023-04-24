package com.java.tutorial.project.funcmodel;


import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAccountServiceImpl implements AccountService<String> {

    private final static String EMPLOYEE_ACCOUNT_TYPE = "manager_type";

    @Override
    public String getAccountName(String accountId) {
        return RandomUtil.randomString(12);
    }

    @Override
    public boolean isAccountType(String accountType) {
        return EMPLOYEE_ACCOUNT_TYPE.equalsIgnoreCase(accountType);
    }
}
