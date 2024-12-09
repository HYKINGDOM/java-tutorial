package com.java.tutorial.project.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyPayVO {
    private String people;
    private BigDecimal money;
}
