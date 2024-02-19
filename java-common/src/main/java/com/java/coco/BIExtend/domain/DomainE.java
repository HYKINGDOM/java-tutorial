package com.java.coco.BIExtend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DomainE extends ParentDomain {
    private String password;

    @Override
    public Integer typeCode() {
        return 5;
    }
}
