package com.java.tutorial.project.common.log;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * 业务操作日志
 *
 *
 */
@Data
@Accessors(chain = true)
public final class BusLogBean {

    /**
     * ID
     */
    private Long id;
    /**
     * 业务名称
     */
    private String busName;
    /**
     * 业务操作描述
     */
    private String busDescrip;
    /**
     * 操作人
     */
    private String operPerson;
    /**
     * 操作时间
     */
    private Date operTime;
    /**
     * 操作来源IP
     */
    private String ipFrom;
    /**
     * 操作参数报文文件
     */
    private String paramFile;

}
