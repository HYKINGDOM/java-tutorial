package com.java.tutorial.project.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ToolProperties {


    private Integer poolCpuNumber;

    private String poolName;
}
