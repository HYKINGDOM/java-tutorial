package com.java.tutorial.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author meta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {

    private int sex;

    private String name;

    private String drlType;
}
