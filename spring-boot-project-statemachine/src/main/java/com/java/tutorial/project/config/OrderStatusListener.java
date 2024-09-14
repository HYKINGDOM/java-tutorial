package com.java.tutorial.project.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ObjectUtil;
import com.java.tutorial.project.constant.OrderStatusChangeEventEnum;
import com.java.tutorial.project.constant.OrderStatusEnum;
import com.java.tutorial.project.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author meta
 * @description: 状态监听
 */
@Slf4j
@Component
@WithStateMachine
@Transactional(rollbackFor = Exception.class)
public class OrderStatusListener {
    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition(Message<OrderStatusChangeEventEnum> message) {
        Order order = getOrderFromMessage(message);
        if (ObjectUtil.isNull(order)) {
            log.error("[支付]信息为空");
            return false;
        }
        order.setOrderStatus(OrderStatusEnum.WAIT_DELIVER);
        log.info("支付，状态机反馈信息：{}", message.getHeaders());
        return true;
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderStatusChangeEventEnum> message) {
        Order order = getOrderFromMessage(message);
        if (ObjectUtil.isNull(order)) {
            log.error("[待收货]信息为空");
            return false;
        }
        order.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE);
        log.info("发货，状态机反馈信息：{}", message.getHeaders());
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean receiveTransition(Message<OrderStatusChangeEventEnum> message) {
        Order order = getOrderFromMessage(message);
        if (ObjectUtil.isNull(order)) {
            log.error("[收货]信息为空");
            return false;
        }
        order.setOrderStatus(OrderStatusEnum.FINISH);
        log.info("收货，状态机反馈信息：{}", message.getHeaders());
        return true;
    }

    private static Order getOrderFromMessage(Message<OrderStatusChangeEventEnum> message) {
        Object objectOrder = message.getHeaders().get("order");
        if (ObjectUtil.isNull(objectOrder)) {
            log.error("订单信息为空");
            return null;
        }
        if (!(objectOrder instanceof Order)) {
            log.error("订单信息类型错误");
            return null;
        }

        return Convert.convert(new TypeReference<>() {}, objectOrder);
    }
}


