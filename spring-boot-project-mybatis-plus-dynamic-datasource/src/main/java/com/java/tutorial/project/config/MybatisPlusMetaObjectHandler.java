package com.java.tutorial.project.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * 审计字段自动填充
 *
 * @author hy
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {


    /**
     * 默认操作人
     */
    private static final String DEFAULT_OPERATOR = "SystemAdmin";


    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("delFlag", 0, metaObject);
        this.setFieldValByName("vision", 1, metaObject);
        this.setFieldValByName("createdBy", DEFAULT_OPERATOR, metaObject);
        this.setFieldValByName("createdTime", new Date(), metaObject);
        this.setFieldValByName("updatedBy", DEFAULT_OPERATOR, metaObject);
        this.setFieldValByName("updatedTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object fieldValByVersion = this.getFieldValByName("vision", metaObject);
        String anElse = Optional.ofNullable(fieldValByVersion).map(String::valueOf).orElse("1");
        int version = Integer.parseInt(anElse);
        this.setFieldValByName("vision", version + 1, metaObject);
        this.setFieldValByName("updatedBy", DEFAULT_OPERATOR, metaObject);
        this.setFieldValByName("updatedTime", new Date(), metaObject);

    }
}
