package com.java.tutorial.project.controller;


import com.java.tutorial.project.domain.Account;
import com.java.tutorial.project.repository.entity.AddressEntity;
import com.java.tutorial.project.service.WebfluxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/flux")
public class FluxDemoController {

    private final WebfluxService webfluxService;


    @GetMapping("/info")
    public Flux<String> getInfo() {
        return Flux.just(webfluxService.getInfo(1234567L));
    }


    @GetMapping("/getAccount")
    public Flux<Account> getAccount() {
        return Flux.just(webfluxService.getAccount(1234567L));
    }

    @GetMapping("/getAllAccounts")
    public Flux<List<Account>> getAllAccounts() {
        return Flux.just(webfluxService.getAllAccounts());
    }


    @GetMapping("/getAddressToString")
    public Flux<String> getAddressToString() {
        return webfluxService.getAddressToString();
    }


    @GetMapping("/save")
    public Mono<AddressEntity> saveAddressEntity() {
        return webfluxService.saveAddressEntity();
    }


    @PostMapping("/options")
    public Mono<AddressEntity> optionsEntity() {
        return webfluxService.saveAddressEntity();
    }
}
