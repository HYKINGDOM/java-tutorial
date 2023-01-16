package com.java.tutorial.project.service;


import com.java.tutorial.project.domain.Account;
import com.java.tutorial.project.domain.Address;
import com.java.tutorial.project.repository.entity.AddressEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface WebfluxService {


    String getInfo(Long id);


    Account getAccount(Long id);


    List<Account> getAllAccounts();


    Flux<Address> getAddress(Integer addressId);

    Flux<Address> getAllAddress();


    Flux<String> getAddressToString();

    Flux<String> getAllAddressToString();


    Mono<AddressEntity> saveAddressEntity();
}
