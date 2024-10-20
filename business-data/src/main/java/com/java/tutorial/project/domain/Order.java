package com.java.tutorial.project.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author meta
 */
@Data
@Builder
public class Order {

    Long id;

    String orderNumber;

    Double money;

    Integer status;
}
