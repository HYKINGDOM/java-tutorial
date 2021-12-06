package com.java.util.javautil.utils.bean.mapstruct;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentVo {
    private String userName;
    private String userId;
    private String address;
    private String school;
    private int age;
    private String emailAddress;

    private String nikeName;
}

