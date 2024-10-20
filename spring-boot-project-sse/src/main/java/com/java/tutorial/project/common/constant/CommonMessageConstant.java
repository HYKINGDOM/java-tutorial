package com.java.tutorial.project.common.constant;

import com.java.tutorial.project.common.vo.ContentVo;

/**
 * @author meta
 */
public class CommonMessageConstant {
    public final static ContentVo CONTENT = ContentVo.builder().title("no_persistent_connection_created")
        .content("no_persistent_connection_created_time %s").build();

}
