package com.java.tutorial.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 请求参数封装类
 *
 * @author kscs
 */
@Data
public class DataEventRequest {

    private String data;
    private String userId;

    @JsonIgnore
    private String traceId;

    @JsonIgnore
    private DeferredResult<DataEventResponse> deferredResult;
}
