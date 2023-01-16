package com.java.tutorial.project.controller;


import com.java.tutorial.project.domain.Account;
import com.java.tutorial.project.service.WebfluxService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/mono")
public class MonoDemoController {

    private final WebfluxService webfluxService;


    @GetMapping("/info")
    public Mono<String> getInfo() {
        return Mono.just(webfluxService.getInfo(1234567L));
    }


    @GetMapping("/getAccount")
    public Mono<Account> getAccount() {
        return Mono.just(webfluxService.getAccount(1234567L));
    }

    @GetMapping("/getAllAccounts")
    public Mono<List<Account>> getAllAccounts() {
        return Mono.just(webfluxService.getAllAccounts());
    }

}
