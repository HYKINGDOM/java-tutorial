package com.java.coco.BIExtend.domain;

import lombok.Data;

@Data
public class DomainB extends ParentDomain {

    private String nickName;

    @Override
    public Integer typeCode() {
        return 2;
    }

}
