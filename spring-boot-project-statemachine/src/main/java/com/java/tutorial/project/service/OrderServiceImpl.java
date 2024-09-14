package com.java.tutorial.project.service;

import com.google.common.collect.Maps;
import com.java.tutorial.project.constant.OrderStatusChangeEventEnum;
import com.java.tutorial.project.constant.OrderStatusEnum;
import com.java.tutorial.project.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @author meta
 * @description: 订单服务
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private StateMachine<OrderStatusEnum, OrderStatusChangeEventEnum> orderStateMachine;

    private long id = 1L;

    private final Map<Long, Order> orders = Maps.newConcurrentMap();

    @Override
    public Order create() {
        Order order = new Order();
        order.setOrderStatus(OrderStatusEnum.WAIT_PAYMENT);
        order.setOrderId(id++);
        orders.put(order.getOrderId(), order);
        log.info("订单创建成功:{}", order);
        return order;
    }

    @Override
    public Order pay(long id) {
        Order order = orders.get(id);
        log.info("尝试支付，订单号：:{}", order);
        Message<OrderStatusChangeEventEnum> message =
            MessageBuilder.withPayload(OrderStatusChangeEventEnum.PAYED).setHeader("order", order).build();
        if (!sendEvent(message)) {
            log.info("支付失败, 状态异常，订单号：{}", order);
        }
        return orders.get(id);
    }

    @Override
    public Order deliver(long id) {
        Order order = orders.get(id);
        log.info(" 尝试发货，订单号：{}", id);
        if (!sendEvent(
            MessageBuilder.withPayload(OrderStatusChangeEventEnum.DELIVERY).setHeader("order", order).build())) {
            log.info(" 发货失败，状态异常，订单号：{}", id);
        }
        return orders.get(id);
    }

    @Override
    public Order receive(long id) {
        Order order = orders.get(id);
        log.info(" 尝试收货，订单号：{}", id);
        if (!sendEvent(
            MessageBuilder.withPayload(OrderStatusChangeEventEnum.RECEIVED).setHeader("order", order).build())) {
            log.info(" 收货失败，状态异常，订单号：{}", id);
        }
        return orders.get(id);
    }

    @Override
    public Map<Long, Order> getOrders() {
        return orders;
    }

    /**
     * 发送状态转换事件
     *
     * @param message
     * @return
     */
    private boolean sendEvent(Message<OrderStatusChangeEventEnum> message) {
        boolean result = false;
        try {
            orderStateMachine.start();
            result = orderStateMachine.sendEvent(message);
        } catch (Exception e) {
            log.error("发送状态转换事件失败", e);
        } finally {
            if (Objects.nonNull(message)) {
                Order order = (Order)message.getHeaders().get("order");
                if (Objects.nonNull(order) && Objects.equals(order.getOrderStatus(), OrderStatusEnum.FINISH)) {
                    log.info("订单完成，订单号：{}", order.getOrderId());
                    //orderStateMachine.stop();
                }
            }
        }
        return result;
    }
}

