package com.java.util.javautil.BIExtend.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DomainE extends ParentDomain{
    @Override
    public Integer typeCode() {
        return 5;
    }

    private String password;
}
