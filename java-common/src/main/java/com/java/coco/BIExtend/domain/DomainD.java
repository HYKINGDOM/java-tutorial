package com.java.coco.BIExtend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DomainD extends ParentDomain {
    private String emailAddress;

    @Override
    public Integer typeCode() {
        return 4;
    }
}
