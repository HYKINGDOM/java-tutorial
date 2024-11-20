package com.java.tutorial.project.infrastucture.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 消息发送记录
 * @author meta
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "message_record")
@Accessors(chain = true)
public class MessageRecordEntity {


    private String clientId;

    private Integer type;

    private String createTime;

    private String content;

    private Integer status;



}
