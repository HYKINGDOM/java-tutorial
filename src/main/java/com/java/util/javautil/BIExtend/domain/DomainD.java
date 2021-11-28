package com.java.util.javautil.BIExtend.domain;

import lombok.Data;

@Data
public class DomainD extends ParentDomain{
    @Override
    public Integer typeCode() {
        return 4;
    }

    private String emailAddress;
}
