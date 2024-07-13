package com.java.coco.BIExtend.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Builder
@ToString
@Data
public class DomainA extends ParentDomain {
    private Integer age;

    @Override
    public Integer typeCode() {
        return 1;
    }
}
