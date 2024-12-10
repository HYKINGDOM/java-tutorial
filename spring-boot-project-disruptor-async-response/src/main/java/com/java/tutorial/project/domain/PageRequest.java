package com.java.tutorial.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author kscs
 */
@AllArgsConstructor
@Data
public class PageRequest {

    /**
     * 分页起始页码
     */
    private int pageNumber = 0;
    /**
     * 每页查询的数量
     */
    private int pageSize = 1000;

    private String sort;


    public static PageRequest of(int pageNumber, int pageSize, String sort) {
        return new PageRequest(pageNumber, pageSize, sort);
    }
}
