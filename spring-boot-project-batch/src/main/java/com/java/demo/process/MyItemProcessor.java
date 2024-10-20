package com.java.demo.process;

import com.java.demo.dto.BlogInfo;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * @author meta
 */
public class MyItemProcessor extends ValidatingItemProcessor<BlogInfo> {
    @Override
    public BlogInfo process(BlogInfo item) throws ValidationException {
        //需要执行super.process(item)才会调用自定义校验器
        super.process(item);
        //对数据进行简单的处理
        if ("springboot".equals(item.getBlogItem())) {
            item.setBlogTitle("spring boot batch");
        } else {
            item.setBlogTitle("未知系列");
        }
        return item;
    }
}
