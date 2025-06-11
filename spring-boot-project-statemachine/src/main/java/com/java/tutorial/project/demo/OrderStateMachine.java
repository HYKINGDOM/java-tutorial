package com.java.tutorial.project.demo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author meta
 */
public class OrderStateMachine {

    // 状态转移规则 (当前状态 -> 允许的下一个状态集合)
    private static final Map<OrderStatus, Set<OrderStatus>> TRANSITION_RULES = new EnumMap<>(OrderStatus.class);

    static {
        TRANSITION_RULES.put(OrderStatus.DRAFT, EnumSet.of(OrderStatus.PENDING_PAY));
        TRANSITION_RULES.put(OrderStatus.PENDING_PAY, EnumSet.of(
                OrderStatus.PAID, OrderStatus.CANCELLED, OrderStatus.CLOSED));
        TRANSITION_RULES.put(OrderStatus.PAID, EnumSet.of(
                OrderStatus.PROCESSING, OrderStatus.REFUNDING));
        TRANSITION_RULES.put(OrderStatus.PROCESSING, EnumSet.of(
                OrderStatus.SHIPPED, OrderStatus.COMPLETED));
        TRANSITION_RULES.put(OrderStatus.SHIPPED, EnumSet.of(
                OrderStatus.COMPLETED, OrderStatus.REFUNDING));
        TRANSITION_RULES.put(OrderStatus.REFUNDING, EnumSet.of(
                OrderStatus.CLOSED, OrderStatus.PAID, OrderStatus.SHIPPED, OrderStatus.COMPLETED));
        // 终态不允许再转换
        EnumSet.allOf(OrderStatus.class).stream()
                .filter(OrderStatus::isTerminal)
                .forEach(s -> TRANSITION_RULES.put(s, Collections.emptySet()));
    }

    // 状态变更历史存储 (实际项目中应使用数据库)
    private final Map<Long, List<OrderStateLog>> stateHistoryStore = new ConcurrentHashMap<>();

    /**
     * 执行状态转移
     * @param order 订单对象
     * @param newStatus 目标状态
     * @param eventType 触发事件类型
     * @param operator 操作者
     */
    public synchronized void transition(Order order, OrderStatus newStatus,
                                        String eventType, String operator) {

        // 1. 检查当前状态是否允许转换
        OrderStatus currentStatus = order.getStatus();
        if (!isTransitionAllowed(currentStatus, newStatus)) {
            throw new IllegalStateTransitionException(
                    String.format("非法状态转换: %s -> %s", currentStatus, newStatus));
        }

        // 2. 记录变更前的状态
        OrderStatus originalStatus = currentStatus;

        // 3. 执行状态变更 (乐观锁检查在DAO层实现)
        order.transitionTo(newStatus);

        // 4. 记录状态变更历史
        recordStateChange(order.getId(), originalStatus, newStatus, eventType, operator);
    }

    /**
     * 检查状态转换是否允许
     */
    public boolean isTransitionAllowed(OrderStatus current, OrderStatus next) {
        // 终态检查
        if (current.isTerminal()) {
            return false;
        }

        Set<OrderStatus> allowedStates = TRANSITION_RULES.get(current);
        return allowedStates != null && allowedStates.contains(next);
    }

    /**
     * 记录状态变更历史
     */
    private void recordStateChange(Long orderId, OrderStatus fromState,
                                   OrderStatus toState, String eventType, String operator) {
        OrderStateLog log = new OrderStateLog(
                orderId, fromState, toState, eventType, operator);

        stateHistoryStore.computeIfAbsent(orderId, k -> new ArrayList<>())
                .add(log);
    }

    /**
     * 获取订单状态变更历史
     */
    public List<OrderStateLog> getStateHistory(Long orderId) {
        return Collections.unmodifiableList(
                stateHistoryStore.getOrDefault(orderId, new ArrayList<>()));
    }

    // 自定义异常
    public static class IllegalStateTransitionException extends RuntimeException {
        public IllegalStateTransitionException(String message) {
            super(message);
        }
    }
}
