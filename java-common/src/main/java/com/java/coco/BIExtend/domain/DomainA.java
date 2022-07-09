package com.java.coco.BIExtend.domain;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class DomainA extends ParentDomain{
    @Override
    public Integer typeCode() {
        return 1;
    }

    private Integer age;
}
