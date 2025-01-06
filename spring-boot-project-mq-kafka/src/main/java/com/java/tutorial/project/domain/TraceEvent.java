package com.java.tutorial.project.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author meta
 */
@Builder
@Data
public class TraceEvent {

    private String messageId;

    private String consumerGroup;

    private long consumerId;

    private long timestamp;

    private String traceId;

    private String phase;

    private String topic;

    private int partition;

    private long offset;

    private String status;

    private String errorMessage;

}
