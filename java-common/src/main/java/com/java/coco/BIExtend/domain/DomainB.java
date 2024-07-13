package com.java.coco.BIExtend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DomainB extends ParentDomain {

    private String nickName;

    @Override
    public Integer typeCode() {
        return 2;
    }

}
