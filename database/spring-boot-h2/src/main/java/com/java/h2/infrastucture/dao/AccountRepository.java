package com.java.h2.infrastucture.dao;


import com.java.h2.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author HY
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
