package com.example.demo.infrastucture.dao;

import com.example.demo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author meta
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
