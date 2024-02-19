package com.example.demo.controller;

import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SmsController {

    @Autowired
    private TransactionService propagationRequiredNewTransactionServiceImpl;

    @GetMapping("/sms/{transactionType}")
    public String executeTransactionalAction(@PathVariable String transactionType) {
        boolean transaction = propagationRequiredNewTransactionServiceImpl.isTransaction(transactionType);

        return "getExecute(transactionType, account)";
    }
}
