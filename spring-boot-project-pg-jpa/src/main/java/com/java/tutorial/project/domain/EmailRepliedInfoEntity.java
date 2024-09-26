package com.java.tutorial.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * 邮件回复表 实体类。
 *
 * @author SuperMan
 * @since 2024-02-04
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_replied_info")
public class EmailRepliedInfoEntity implements Serializable {

    /**
     * 主键Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 计划id
     */
    private Long planId;

    /**
     * 达人id
     */
    private String creatorId;

    /**
     * 发件人邮箱
     */
    private String fromAccount;

    /**
     * 收件人邮箱
     */
    private String toAccount;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 邮件id
     */
    private String emailId;

    /**
     * 入库时间
     */
    @CreatedDate
    private LocalDateTime createTime;

    @CreatedDate
    @LastModifiedDate
    private LocalDateTime updateTime;

    /**
     * 对话id
     */
    private String conversationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmailRepliedInfoEntity that = (EmailRepliedInfoEntity)o;
        return Objects.equals(id, that.id) && Objects.equals(planId, that.planId) && Objects.equals(creatorId,
            that.creatorId) && Objects.equals(fromAccount, that.fromAccount) && Objects.equals(toAccount,
            that.toAccount) && Objects.equals(content, that.content) && Objects.equals(sendTime,
            that.sendTime) && Objects.equals(emailId, that.emailId) && Objects.equals(createTime,
            that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(conversationId,
            that.conversationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planId, creatorId, fromAccount, toAccount, content, sendTime, emailId, createTime,
            updateTime, conversationId);
    }
}
