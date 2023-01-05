package com.java.tutorial.project.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {


    private String userAccountNum;

    private String userName;

    private String niceName;

    private Integer age;

    private String passWord;

    private String userEmail;
}
