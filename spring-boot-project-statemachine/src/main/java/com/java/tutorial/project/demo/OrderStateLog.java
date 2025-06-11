package com.java.tutorial.project.demo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 状态变更历史记录
 */
@Data
public class OrderStateLog {
    private Long id;
    private Long orderId;
    private OrderStatus fromState;
    private OrderStatus toState;
    private String eventType;
    private String operator; // 操作人(用户/系统/定时任务)
    private LocalDateTime createdAt;

    public OrderStateLog(Long orderId, OrderStatus fromState, OrderStatus toState,
                         String eventType, String operator) {
        this.orderId = orderId;
        this.fromState = fromState;
        this.toState = toState;
        this.eventType = eventType;
        this.operator = operator;
        this.createdAt = LocalDateTime.now();
    }

    // Getters
}
