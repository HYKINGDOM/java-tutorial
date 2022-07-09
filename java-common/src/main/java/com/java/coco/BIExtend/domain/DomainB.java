package com.java.coco.BIExtend.domain;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
public class DomainB extends ParentDomain {


    @Override
    public Integer typeCode() {
        return 2;
    }


    private String nickName;


}
