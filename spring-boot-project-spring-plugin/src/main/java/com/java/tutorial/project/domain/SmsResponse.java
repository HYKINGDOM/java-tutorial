package com.java.tutorial.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsResponse {

    private String code;

    private String message;

    private Boolean success;

    private Object result;
}
