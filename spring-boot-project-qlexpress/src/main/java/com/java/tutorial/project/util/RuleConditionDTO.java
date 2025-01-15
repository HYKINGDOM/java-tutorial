package com.java.tutorial.project.util;

import lombok.Data;

import java.util.List;

@Data
public class RuleConditionDTO {

    private String condition;

    private String rule;

    private String symbol;

    private String value;

    private List<RuleConditionDTO> nestedConditions;

}
