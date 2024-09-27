package com.java.tutorial.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
@Table(name = "send_email_Info")
@EntityListeners(AuditingEntityListener.class)
public class SendEmailInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /** 发送方邮件 */
    private String fromAccount;
    /** 收件人邮件 */
    private String toAccount;
    /** 邮件内容 */
    private String content;
    /** 发送时间 */
    private LocalDateTime sendTime;
    /** 邮件ID */
    private String emailId;
    /** conversationId */
    private String conversationId;
    /** 乐观锁 */
    @Version
    private Integer version;
    /** 创建人 */
    @CreatedBy
    private Long createdBy;
    /** 创建时间 */
    @CreatedDate
    private LocalDateTime createdTime;
    /** 更新人 */
    @LastModifiedBy
    private Long updatedBy;
    /** 更新时间 */
    @LastModifiedDate
    @CreatedDate
    private LocalDateTime updatedTime;

}
