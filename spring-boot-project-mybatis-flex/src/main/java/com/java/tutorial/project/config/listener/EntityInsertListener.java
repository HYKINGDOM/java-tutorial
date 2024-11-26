package com.java.tutorial.project.config.listener;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.java.tutorial.project.config.SecurityUtils;
import com.java.tutorial.project.domain.BaseEntity;
import com.mybatisflex.annotation.InsertListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Optional;

/**
 * Entity实体类全局插入数据监听器
 *
 * @author dataprince数据小王子
 */
@Slf4j
public class EntityInsertListener implements InsertListener {

    @Override
    public void onInsert(Object entity) {
        if (ObjectUtil.isNotNull(entity) && (entity instanceof BaseEntity)) {
            BaseEntity baseEntity = (BaseEntity)entity;

            String loginUserName = SecurityUtils.getDefaultLoginUserName();
            Date dateTime = Optional.ofNullable(baseEntity.getCreateTime()).orElse(new Date());
            if (StrUtil.isBlank(baseEntity.getCreateBy())) {
                baseEntity.setCreateBy(loginUserName);
            }
            if (StrUtil.isBlank(baseEntity.getUpdateBy())) {
                baseEntity.setUpdateBy(loginUserName);
            }
            baseEntity.setCreateTime(dateTime);
            baseEntity.setUpdateTime(dateTime);
        }
    }

}
