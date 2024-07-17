package com.java.tutorial.project.util;

import com.mybatisflex.core.paginate.Page;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分页查询实体类
 *
 * @author meta
 */

@Data
public class PageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 排序列
     */
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    private String isAsc;

    /**
     * 当前记录起始索引 默认值
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * 每页显示记录数 默认值 默认查全部
     */
    public static final int DEFAULT_PAGE_SIZE = Integer.MAX_VALUE;

    /**
     * 构造分页查询参数
     *
     * @param <T>
     * @return
     */
    public static <T> Page<T> build() {
        Integer pageNum = DEFAULT_PAGE_NUM;
        Integer pageSize = DEFAULT_PAGE_SIZE;
        if (pageNum <= 0) {
            pageNum = DEFAULT_PAGE_NUM;
        }
        Page<T> page = new Page<>(pageNum, pageSize);
        return page;
    }

}
