package com.java.coco.utils.bean.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentVo {
    private String userName;
    private String userId;
    private String address;
    private String school;
    private int age;
    private String emailAddress;

    private String nikeName;
}

