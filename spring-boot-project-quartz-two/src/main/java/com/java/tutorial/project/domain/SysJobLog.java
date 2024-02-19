package com.java.tutorial.project.domain;

import com.mybatisflex.annotation.Table;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 定时任务调度日志表 sys_job_log
 *
 * @author meta
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "sys_job_log")
public class SysJobLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** ID */
    private Long jobLogId;

    /** 任务名称 */
    private String jobName;

    /** 任务组名 */
    private String jobGroup;

    /** 调用目标字符串 */
    private String invokeTarget;

    /** 日志信息 */
    private String jobMessage;

    /** 执行状态（0正常 1失败） */
    private String status;

    /** 异常信息 */
    private String exceptionInfo;

    /** 开始时间 */
    private Date startTime;

    /** 停止时间 */
    private Date stopTime;

}
