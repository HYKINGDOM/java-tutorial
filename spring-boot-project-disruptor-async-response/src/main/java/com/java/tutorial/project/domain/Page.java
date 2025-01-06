package com.java.tutorial.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author meta
 * @param <T>
 */
@AllArgsConstructor
@Data
public class Page<T> {

    /**
     * 数据
     */
    private List<T> content;

    /**
     * 是否有下一页
     */
    private boolean hasNext;
}
