package com.java.tutorial.project.domain;

import com.java.tutorial.project.constant.OrderStatusEnum;
import lombok.Data;

/**
 * @author meta
 */
@Data
public class Order {
    private Long orderId;
    private OrderStatusEnum orderStatus;
}

