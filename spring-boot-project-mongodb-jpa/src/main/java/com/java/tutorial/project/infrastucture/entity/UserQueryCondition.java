package com.java.tutorial.project.infrastucture.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserQueryCondition {

    private String userName;

    private String shortName;

    private String nikeName;
}
