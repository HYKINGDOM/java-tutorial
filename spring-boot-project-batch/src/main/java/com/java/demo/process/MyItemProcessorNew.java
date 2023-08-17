package com.java.demo.process;

import com.java.demo.dto.BlogInfo;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 *
 */
public class MyItemProcessorNew extends ValidatingItemProcessor<BlogInfo> {
    @Override
    public BlogInfo process(BlogInfo item) throws ValidationException {
        //需要执行super.process(item)才会调用自定义校验器
        super.process(item);
        //对数据进行简单的处理
        int authorId = Integer.parseInt(item.getBlogAuthor());
        if (authorId < 20000) {
            item.setBlogTitle("这是都是小于20000的数据");
        } else if (authorId > 20000 && authorId < 30000) {
            item.setBlogTitle("这是都是小于30000但是大于20000的数据");
        } else {
            item.setBlogTitle("大于30000的数据");
        }
        return item;
    }
}
