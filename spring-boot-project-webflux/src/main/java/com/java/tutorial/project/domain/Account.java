package com.java.tutorial.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long accountId;

    private String accountName;

    private String nickname;

    private String emailAddress;

    private Integer phone;

}
