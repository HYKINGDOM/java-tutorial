package com.example.demo.service;

import com.example.demo.domain.Account;

public interface TransactionService {

    boolean isTransaction(String transactionType);

    String execute(Account account);
}
