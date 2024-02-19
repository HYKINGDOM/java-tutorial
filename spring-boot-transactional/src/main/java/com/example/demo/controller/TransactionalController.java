package com.example.demo.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.demo.domain.Account;
import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TransactionalController {

    private final List<TransactionService> transactionServices;

    @GetMapping("/transaction/{transactionType}")
    public String executeTransactionalAction(@PathVariable String transactionType) {
        Account account =
            Account.builder().niceName("tony").age(RandomUtil.randomInt()).userName(RandomUtil.randomString(10))
                .password(RandomUtil.randomString(12)).userEmail(RandomUtil.randomString("@", 18))
                .userAccountNum(transactionType).build();
        return getExecute(transactionType, account);
    }

    private String getExecute(String transactionType, Account account) {
        return transactionServices.stream().filter(e -> e.isTransaction(transactionType)).findFirst()
            .orElseThrow(NullPointerException::new).execute(account);
    }

}
