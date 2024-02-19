package com.java.tutorial.project.domain.schema;

import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * @author meta
 */
@Data
@Table(value = "user_info", schema = "public")
public class UserInfo {

    private Long id;

    private String userId;

    private String userName;

    private String password;
}
