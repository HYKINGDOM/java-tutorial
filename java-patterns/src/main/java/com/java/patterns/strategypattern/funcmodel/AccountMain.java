package com.java.util.javautil.patterns.strategypattern.funcmodel;

import java.util.ArrayList;
import java.util.List;

public class AccountMain {

    private static List<AccountService> accountServiceList = new ArrayList<>();

    public static void main(String[] args) {
        String accountType = "manager_type";
        String accountId = accountServiceList
                .stream()
                .filter(e -> e.isAccountType(accountType))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getAccountName("test");
        System.out.println(accountId);
    }
}
