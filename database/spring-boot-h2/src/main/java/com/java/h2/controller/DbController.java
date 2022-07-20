package com.java.h2.controller;


import cn.hutool.core.util.RandomUtil;
import com.java.h2.domain.Account;
import com.java.h2.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DbController {


    private final DbService dbServices;


    @GetMapping("/save-db/{transactionType}")
    public String executeTransactionalAction(@PathVariable String transactionType) {
        Account account = Account.builder()
                .niceName("tony")
                .age(RandomUtil.randomInt())
                .userName(RandomUtil.randomString(10))
                .password(RandomUtil.randomString(12))
                .userEmail(RandomUtil.randomString("@", 18))
                .userAccountNum(transactionType)
                .build();

        return dbServices.execute(account);
    }

}
