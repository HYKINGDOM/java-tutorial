package com.java.tutorial.project.domain;

import lombok.Data;

@Data
public class TraceContext {


    private String traceId;

    private String messageId;

    private long timestamp;

    private String source;

    public TraceContext(String traceId, String messageId, long timestamp, String source) {
        this.traceId = traceId;
        this.messageId = messageId;
        this.timestamp = timestamp;
        this.source = source;
    }
}
