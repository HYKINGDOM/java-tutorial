package com.java.tutorial.project.domain;

import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * @author meta
 */
@Data
@Table(value = "talent_schema", schema = "public")
public class TalentSchema {

    private Long id;

    private String talentId;

    private String schemaName;
}
