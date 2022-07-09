package com.java.func.sortBy;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DomainUser {

    private Long id;

    private String name;

    private Integer age;

    private boolean sex;


}
