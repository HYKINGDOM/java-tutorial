package com.java.tutorial.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author meta
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeInfo {

    private String name;

    private Integer age;
}
