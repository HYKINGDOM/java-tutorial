package com.java.tutorial.project.domain;

import lombok.Data;

/**
 * 入参DTO，Command代表会执行修改操作
 *
 * @author meta
 */
@Data
public class ArticleTitleModifyDto {

    private String articleId;

    private String title;

    private String content;

}
