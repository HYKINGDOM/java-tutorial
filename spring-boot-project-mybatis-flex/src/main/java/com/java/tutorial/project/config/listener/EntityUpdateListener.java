package com.java.tutorial.project.config.listener;

import cn.hutool.core.util.ObjectUtil;
import com.java.tutorial.project.config.SecurityUtils;
import com.java.tutorial.project.domain.BaseEntity;
import com.mybatisflex.annotation.UpdateListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Entity实体类全局更新数据监听器
 * @author meta
 */
@Slf4j
public class EntityUpdateListener implements UpdateListener {
    @Override
    public void onUpdate(Object entity) {
        if (ObjectUtil.isNotNull(entity) && (entity instanceof BaseEntity)) {
            BaseEntity baseEntity = (BaseEntity)entity;

            String loginUserName = SecurityUtils.getDefaultLoginUserName();
            baseEntity.setUpdateBy(loginUserName);
            baseEntity.setUpdateTime(new Date());
        }
    }
}
