package com.java.tutorial.project.config;

import com.java.tutorial.project.constant.OrderStatusChangeEventEnum;
import com.java.tutorial.project.constant.OrderStatusEnum;
import com.java.tutorial.project.domain.Order;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 状态监听
 */
@Component
@WithStateMachine
@Transactional
public class OrderStatusListener {
    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition(Message<OrderStatusChangeEventEnum> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setOrderStatus(OrderStatusEnum.WAIT_DELIVER);
        System.out.println("支付，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderStatusChangeEventEnum> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE);
        System.out.println("发货，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean receiveTransition(Message<OrderStatusChangeEventEnum> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setOrderStatus(OrderStatusEnum.FINISH);
        System.out.println("收货，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }

}


