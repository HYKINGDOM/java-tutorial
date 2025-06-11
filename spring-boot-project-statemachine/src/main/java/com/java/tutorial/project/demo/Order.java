package com.java.tutorial.project.demo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单实体
 *
 * @author meta
 */
@Data
public class Order {
    private Long id;
    private String orderNumber;
    private OrderStatus status;
    /**
     * 乐观锁版本号
     */
    private int version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 构造函数
     *
     * @param orderNumber
     */
    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
        this.status = OrderStatus.DRAFT;
        this.version = 1;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 状态转移方法
     *
     * @param newStatus
     */
    public void transitionTo(OrderStatus newStatus) {
        if (this.status.isTerminal()) {
            throw new IllegalStateException("终态订单不允许状态变更");
        }
        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();
        this.version++;
    }
}