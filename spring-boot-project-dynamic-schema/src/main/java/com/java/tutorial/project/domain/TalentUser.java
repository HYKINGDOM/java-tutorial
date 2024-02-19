package com.java.tutorial.project.domain;

import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * @author meta
 */
@Data
@Table(value = "talent_user", schema = "public")
public class TalentUser {

    private Long id;

    private String talentId;

    private String userId;

}
