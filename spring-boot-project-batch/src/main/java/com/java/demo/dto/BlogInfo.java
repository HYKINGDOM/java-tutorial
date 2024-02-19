package com.java.demo.dto;

import lombok.Data;

@Data
public class BlogInfo {

    private Integer id;
    private String blogAuthor;
    private String blogUrl;
    private String blogTitle;
    private String blogItem;
}
