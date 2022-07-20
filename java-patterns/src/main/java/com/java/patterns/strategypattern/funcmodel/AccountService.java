package com.java.patterns.strategypattern.funcmodel;

public interface AccountService {

    String getAccountName(String accountId);

    boolean isAccountType(String accountType);
}
