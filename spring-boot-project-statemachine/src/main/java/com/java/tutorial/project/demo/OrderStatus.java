package com.java.tutorial.project.demo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 订单状态枚举
 */
public enum OrderStatus {
    DRAFT(false),
    PENDING_PAY(false),
    PAID(false),
    PROCESSING(false),
    SHIPPED(false),
    COMPLETED(true),   // 终态
    CANCELLED(true),   // 终态
    CLOSED(true),      // 终态
    REFUNDING(false),
    FAILED(true);      // 终态

    private final boolean terminal;

    OrderStatus(boolean terminal) {
        this.terminal = terminal;
    }

    public boolean isTerminal() {
        return terminal;
    }
}
