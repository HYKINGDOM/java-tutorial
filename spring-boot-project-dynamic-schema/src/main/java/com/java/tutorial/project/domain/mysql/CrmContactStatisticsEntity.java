package com.java.tutorial.project.domain.mysql;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * 建联次数统计 实体类。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Table(value = "crm_contact_statistics")
@Data
public class CrmContactStatisticsEntity {

    /**
     * 达人ID
     */
    @Column(value = "creator_id")
    private String creatorId;

    /**
     * 达人名字
     */
    @Column(value = "creator_name")
    private String creatorName;

    /**
     * MCN建联成功次数-私信
     */
    @Column(value = "contact_amount_mcn_private")
    private Long contactAmountMcnPrivate;

    /**
     * MCN建联成功次数-邮件
     */
    @Column(value = "contact_amount_mcn_email")
    private Long contactAmountMcnEmail;

    /**
     * NNX建联成功次数-私信
     */
    @Column(value = "contact_amount_nnx_private")
    private Long contactAmountNnxPrivate;

    /**
     * NNX建联成功次数-邮件
     */
    @Column(value = "contact_amount_nnx_email")
    private Long contactAmountNnxEmail;
}
