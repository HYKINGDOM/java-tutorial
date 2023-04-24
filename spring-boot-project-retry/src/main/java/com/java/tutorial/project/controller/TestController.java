package com.java.tutorial.project.controller;


import com.java.tutorial.project.funcmodel.AccountService;
import com.java.tutorial.project.service.CustomizeRetryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private CustomizeRetryService customizeRetryService;

    @Resource
    private List<AccountService<String>> accountServices;


    @Resource
    private AccountService<Integer> accountService;

    @GetMapping(value = "/demo")
    public String test(@RequestParam Integer code) throws Exception {

        return customizeRetryService.testRetry(code);

    }


    @GetMapping(value = "/demo01")
    public String test01(@RequestParam String type){
        String accountType = "manager_type";
        return accountServices
                .stream()
                .filter(e -> e.isAccountType(type))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getAccountName(accountType);
    }


    @GetMapping(value = "/demo02")
    public String test02(@RequestParam Integer type){
        String accountType = "manager_type";
        return accountService.getAccountName(type);
    }
}
