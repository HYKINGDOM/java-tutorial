package com.java.tutorial.project.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderParam {

    private Integer orderType;

    private String userId;

    private String userName;



}
