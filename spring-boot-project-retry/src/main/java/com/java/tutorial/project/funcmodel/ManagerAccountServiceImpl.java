package com.java.tutorial.project.funcmodel;


import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Component;

@Component
public class ManagerAccountServiceImpl implements AccountService<Integer> {

    private final static String MANAGER_ACCOUNT_TYPE = "manager_type";

    @Override
    public String getAccountName(Integer accountId) {
        return RandomUtil.randomString(accountId);
    }

    @Override
    public boolean isAccountType(String accountType) {
        return MANAGER_ACCOUNT_TYPE.equalsIgnoreCase(accountType);
    }
}
