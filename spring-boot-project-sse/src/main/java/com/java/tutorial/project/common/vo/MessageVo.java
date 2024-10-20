package com.java.tutorial.project.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author meta
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageVo {
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 传输数据体(json)
     */
    private ContentVo content;
    /**
     * 类型，type=1,type=2
     */
    private Integer type;
}
