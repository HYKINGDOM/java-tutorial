package com.java.coco.BIExtend.domain;

import lombok.Data;

/**
 * @author yihur
 */
@Data
public abstract class ParentDomain {

    private Integer UserId;
    private String UserName;

    public abstract Integer typeCode();

}
