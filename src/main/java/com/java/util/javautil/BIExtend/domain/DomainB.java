package com.java.util.javautil.BIExtend.domain;


import lombok.Data;

@Data
public class DomainB extends ParentDomain {


    @Override
    public Integer typeCode() {
        return 2;
    }


    private String nickName;


}
