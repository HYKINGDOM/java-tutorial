package com.java.coco.BIExtend.domain;


import lombok.Data;

/**
 * @author yihur
 */
@Data
public abstract class ParentDomain {

    public abstract Integer typeCode();

    private Integer UserId;

    private String UserName;



}
