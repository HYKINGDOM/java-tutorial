package com.java.coco.BIExtend.domain;

import lombok.Data;

@Data
public class DomainC extends ParentDomain{
    @Override
    public Integer typeCode() {
        return 3;
    }

    private Boolean gender;


}
