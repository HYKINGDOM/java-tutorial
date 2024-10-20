package com.java.tutorial.project.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.text.StrUtil;

/**
 * 消息体
 * @author meta
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentVo {

    private String title;

    private String content;


    public String formatContent(Object... params) {
        content = StrUtil.format(content, params);
        return content;
    }

}
