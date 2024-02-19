package com.example.demo.controller;

import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SmsDemoController {

    @Autowired
    @Qualifier(value = "propagationRequiredTransactionServiceImpl")
    private TransactionService transactionService;

    @Autowired
    @Qualifier(value = "propagationRequiredNewTransactionServiceImpl")
    private TransactionService transactionService02new;

    @GetMapping("/sms/demo1/{transactionType}")
    public String executeTransactionalAction01(@PathVariable String transactionType) {
        boolean transaction = transactionService.isTransaction(transactionType);

        return "getExecute(transactionType, account)";
    }

    @GetMapping("/sms/demo2/{transactionType}")
    public String executeTransactionalAction02(@PathVariable String transactionType) {
        boolean transaction = transactionService02new.isTransaction(transactionType);

        return "getExecute(transactionType, account)";
    }
}
