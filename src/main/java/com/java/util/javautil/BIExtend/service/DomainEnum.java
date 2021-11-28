package com.java.util.javautil.BIExtend.service;

import com.java.util.javautil.BIExtend.domain.*;
import com.java.util.javautil.BIExtend.entity.*;

public enum DomainEnum {

    Domain_A(1, new EntityA()),
    DOMAIN_B(2, new EntityB()),
    DOMAIN_C(3, new EntityC()),
    DOMAIN_D(4, new EntityD()),
    DOMAIN_E(5, new EntityE());


    DomainEnum(Integer typeCode, ParentEntity parentEntity) {
        this.typeCode = typeCode;
        this.parentEntity = parentEntity;
    }


    public static ParentEntity getEntity(Integer typeCode) {
        for (DomainEnum domainEnum : DomainEnum.values()) {
            if (domainEnum.typeCode.equals(typeCode)) {
                return domainEnum.getParentEntity();
            }
        }
        return null;
    }

    private Integer typeCode;

    private ParentEntity parentEntity;


    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public ParentEntity getParentEntity() {
        return parentEntity;
    }

    public void setParentEntity(ParentEntity parentEntity) {
        this.parentEntity = parentEntity;
    }
}
