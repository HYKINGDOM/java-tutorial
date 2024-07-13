package com.java.coco.BIExtend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DomainC extends ParentDomain {
    private Boolean gender;

    @Override
    public Integer typeCode() {
        return 3;
    }

}
