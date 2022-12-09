package com.java.tutorial.tool.domain;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author HY
 */

@Data
@Builder
public class MessageDto {

    private Long businessId;

    private String traceId;

    private Object payload;

    private String version;

    private LocalDateTime currentDateTime;


}
