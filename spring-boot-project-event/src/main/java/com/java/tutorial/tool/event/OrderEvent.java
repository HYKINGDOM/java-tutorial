package com.java.tutorial.tool.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * @author meta
 */
@Getter
@Setter
public class OrderEvent extends ApplicationEvent {

    private Long businessId;

    private String traceId;

    private Object payload;

    private String version;

    private LocalDateTime currentDateTime;

    public OrderEvent(String traceId, Object payload, String version, LocalDateTime currentDateTime, Object source) {
        super(source);
        this.traceId = traceId;
        this.payload = payload;
        this.version = version;
        this.currentDateTime = currentDateTime;
    }

    public OrderEvent(Long businessId, String traceId, Object payload, String version, LocalDateTime currentDateTime,
        Object source) {
        super(source);
        this.businessId = businessId;
        this.traceId = traceId;
        this.payload = payload;
        this.version = version;
        this.currentDateTime = currentDateTime;
    }

    public OrderEvent(Long businessId, String traceId, Object payload, String version, LocalDateTime currentDateTime,
        Object source, Clock clock) {
        super(source, clock);
        this.businessId = businessId;
        this.traceId = traceId;
        this.payload = payload;
        this.version = version;
        this.currentDateTime = currentDateTime;
    }
}
