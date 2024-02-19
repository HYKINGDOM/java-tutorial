package com.java.tutorial.project.repository;

import com.java.tutorial.project.repository.entity.AddressEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressRepository extends ReactiveCrudRepository<AddressEntity, Integer> {

    @Query("select * from address as a where a.address_id = :addressId")
    Mono<AddressEntity> findAllByAddressId(Integer addressId);
}
