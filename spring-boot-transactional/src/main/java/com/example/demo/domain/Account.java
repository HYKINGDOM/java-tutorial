package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author meta
 */
@Entity
@Builder
@Table(name = "t_account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_account_num")
    private String userAccountNum;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "nice_name")
    private String niceName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "password")
    private String password;

    @Column(name = "user_email")
    private String userEmail;
}
