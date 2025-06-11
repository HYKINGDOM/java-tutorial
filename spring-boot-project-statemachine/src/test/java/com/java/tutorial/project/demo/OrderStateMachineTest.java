package com.java.tutorial.project.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class OrderStateMachineTest {

    private OrderStateMachine stateMachine;
    private Order order;

    @BeforeEach
    public void setUp() {
        stateMachine = new OrderStateMachine();
        order = new Order("ORDER-001");
    }

    // 测试正常流程：草稿 -> 待支付 -> 已支付 -> 处理中 -> 已发货 -> 已完成
    @Test
    public void testNormalWorkflow() {
        // DRAFT -> PENDING_PAY
        stateMachine.transition(order, OrderStatus.PENDING_PAY, "OrderSubmitted", "user");
        assertEquals(OrderStatus.PENDING_PAY, order.getStatus());

        // PENDING_PAY -> PAID
        stateMachine.transition(order, OrderStatus.PAID, "PaymentConfirmed", "payment_gateway");
        assertEquals(OrderStatus.PAID, order.getStatus());

        // PAID -> PROCESSING
        stateMachine.transition(order, OrderStatus.PROCESSING, "InventoryReserved", "system");
        assertEquals(OrderStatus.PROCESSING, order.getStatus());

        // PROCESSING -> SHIPPED
        stateMachine.transition(order, OrderStatus.SHIPPED, "GoodsShipped", "warehouse");
        assertEquals(OrderStatus.SHIPPED, order.getStatus());

        // SHIPPED -> COMPLETED
        stateMachine.transition(order, OrderStatus.COMPLETED, "OrderCompleted", "system");
        assertEquals(OrderStatus.COMPLETED, order.getStatus());

        // 验证历史记录
        List<OrderStateLog> history = stateMachine.getStateHistory(order.getId());
        assertEquals(5, history.size());
        assertEquals(OrderStatus.DRAFT, history.get(0).getFromState());
        assertEquals(OrderStatus.COMPLETED, history.get(4).getToState());
    }

    // 测试取消流程：待支付 -> 已取消
    @Test
    public void testCancellationWorkflow() {
        stateMachine.transition(order, OrderStatus.PENDING_PAY, "OrderSubmitted", "user");
        stateMachine.transition(order, OrderStatus.CANCELLED, "UserCancelled", "user");
        assertEquals(OrderStatus.CANCELLED, order.getStatus());

        // 验证终态不可再修改
        try {
            stateMachine.transition(order, OrderStatus.PAID, "ForcePay", "admin");
            fail("Should throw exception for terminal state");
        } catch (OrderStateMachine.IllegalStateTransitionException e) {
            assertTrue(e.getMessage().contains("非法状态转换"));
        }
    }

    // 测试退款流程：已完成 -> 退款中 -> 已关闭
    @Test
    public void testRefundWorkflow() {
        // 先完成订单
        testNormalWorkflow();

        // COMPLETED -> REFUNDING
        stateMachine.transition(order, OrderStatus.REFUNDING, "RefundRequested", "user");
        assertEquals(OrderStatus.REFUNDING, order.getStatus());

        // REFUNDING -> CLOSED
        stateMachine.transition(order, OrderStatus.CLOSED, "RefundCompleted", "finance_system");
        assertEquals(OrderStatus.CLOSED, order.getStatus());
    }

    // 测试非法状态转换：草稿 -> 已完成
    @Test
    public void testInvalidTransition() {
        stateMachine.transition(order, OrderStatus.COMPLETED, "InvalidEvent", "hacker");
    }

    // 测试超时关闭：待支付 -> 已关闭
    @Test
    public void testTimeoutClosure() {
        stateMachine.transition(order, OrderStatus.PENDING_PAY, "OrderSubmitted", "user");
        stateMachine.transition(order, OrderStatus.CLOSED, "PaymentTimeout", "scheduler");
        assertEquals(OrderStatus.CLOSED, order.getStatus());
    }

    // 测试支付失败：待支付 -> 失败
    @Test
    public void testPaymentFailure() {
        stateMachine.transition(order, OrderStatus.PENDING_PAY, "OrderSubmitted", "user");
        stateMachine.transition(order, OrderStatus.FAILED, "PaymentFailed", "payment_gateway");
        assertEquals(OrderStatus.FAILED, order.getStatus());
    }

    // 测试状态变更历史记录
    @Test
    public void testStateHistoryRecording() {
        stateMachine.transition(order, OrderStatus.PENDING_PAY, "OrderSubmitted", "user");
        stateMachine.transition(order, OrderStatus.PAID, "PaymentConfirmed", "payment_gateway");

        List<OrderStateLog> history = stateMachine.getStateHistory(order.getId());
        assertEquals(2, history.size());

        OrderStateLog firstLog = history.get(0);
        assertEquals(OrderStatus.DRAFT, firstLog.getFromState());
        assertEquals(OrderStatus.PENDING_PAY, firstLog.getToState());
        assertEquals("OrderSubmitted", firstLog.getEventType());

        OrderStateLog secondLog = history.get(1);
        assertEquals(OrderStatus.PENDING_PAY, secondLog.getFromState());
        assertEquals(OrderStatus.PAID, secondLog.getToState());
        assertEquals("PaymentConfirmed", secondLog.getEventType());
    }

    // 测试终态后禁止修改
    @Test
    public void testTerminalStateProtection() {
        stateMachine.transition(order, OrderStatus.PENDING_PAY, "OrderSubmitted", "user");
        stateMachine.transition(order, OrderStatus.CANCELLED, "UserCancelled", "user");
        // 尝试修改终态订单
        order.transitionTo(OrderStatus.PAID); // 应抛出IllegalStateException
    }

    // 测试部分退款流程：已支付 -> 退款中 -> 已支付
    @Test
    public void testPartialRefund() {
        stateMachine.transition(order, OrderStatus.PENDING_PAY, "OrderSubmitted", "user");
        stateMachine.transition(order, OrderStatus.PAID, "PaymentConfirmed", "payment_gateway");

        // 发起部分退款
        stateMachine.transition(order, OrderStatus.REFUNDING, "PartialRefundRequest", "user");
        assertEquals(OrderStatus.REFUNDING, order.getStatus());

        // 部分退款完成，返回已支付状态
        stateMachine.transition(order, OrderStatus.PAID, "PartialRefundCompleted", "finance_system");
        assertEquals(OrderStatus.PAID, order.getStatus());
    }
}
