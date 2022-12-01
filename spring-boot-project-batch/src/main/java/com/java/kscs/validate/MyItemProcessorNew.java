package com.java.kscs.validate;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * @Author : JCccc
 * @Description :
 **/
public class MyItemProcessorNew extends ValidatingItemProcessor<BlogInfo> {
    @Override
    public BlogInfo process(BlogInfo item) throws ValidationException {
        /**
         * 需要执行super.process(item)才会调用自定义校验器
         */
        super.process(item);
        /**
         * 对数据进行简单的处理
         */
        Integer authorId= Integer.valueOf(item.getBlogAuthor());
        if (authorId<20000) {
            item.setBlogTitle("这是都是小于20000的数据");
        } else if (authorId>20000 && authorId<30000){
            item.setBlogTitle("这是都是小于30000但是大于20000的数据");
        }else {
            item.setBlogTitle("旧书不厌百回读");
        }
        return item;
    }
}
