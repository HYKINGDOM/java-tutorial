package com.java.tutorial.project.funcmodel;

public interface AccountService<T> {

    String getAccountName(T accountId);

    boolean isAccountType(String accountType);
}
