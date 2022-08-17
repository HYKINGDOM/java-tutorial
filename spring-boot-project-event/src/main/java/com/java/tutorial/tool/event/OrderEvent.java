package com.java.tutorial.tool.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author HY
 */
@Setter
@Getter
public class OrderEvent extends ApplicationEvent {

    private String orderId;

    public OrderEvent(String orderId, Object source) {
        super(source);
        this.orderId = orderId;
    }
}
