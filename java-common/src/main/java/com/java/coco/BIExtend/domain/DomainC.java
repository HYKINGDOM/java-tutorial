package com.java.coco.BIExtend.domain;

import lombok.Data;

@Data
public class DomainC extends ParentDomain {
    private Boolean gender;

    @Override
    public Integer typeCode() {
        return 3;
    }

}
