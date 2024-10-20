package com.java.tutorial.project.infrastructure.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.tutorial.project.infrastructure.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author meta
 */
@Mapper
@DS("mysql")
public interface SysUserDao extends BaseMapper<SysUserEntity> {
}
