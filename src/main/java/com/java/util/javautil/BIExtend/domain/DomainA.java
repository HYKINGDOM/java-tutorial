package com.java.util.javautil.BIExtend.domain;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DomainA extends ParentDomain{
    @Override
    public Integer typeCode() {
        return 1;
    }

    private Integer age;
}
