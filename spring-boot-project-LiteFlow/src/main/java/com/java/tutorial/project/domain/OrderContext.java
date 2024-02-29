package com.java.tutorial.project.domain;

import lombok.Data;

import java.util.List;

/**
 * @author meta
 */
@Data
public class OrderContext {

    private String orderId;

    private List<String> executeRuleList;
}
