package com.java.tutorial.project.common.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "connection_info")
@Accessors(chain = true)
@Builder
public class ConnectionInfo {
    /**
     * 长连接id,文档唯一标志
     */
    @Id
    private String clientId;
    /**
     * 连接的用途
     */
    private Integer type;
    /**
     * 建立连接时间
     */
    private String createTime;
    /**
     * 最近一次通信时间
     */
    private String lastContactTime;
}
