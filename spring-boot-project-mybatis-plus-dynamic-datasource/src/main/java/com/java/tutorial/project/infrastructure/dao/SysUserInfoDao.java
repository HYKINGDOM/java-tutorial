package com.java.tutorial.project.infrastructure.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.tutorial.project.infrastructure.entity.SysUserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author meta
 */
@Mapper
@DS("postgresql")
public interface SysUserInfoDao extends BaseMapper<SysUserInfoEntity> {
}
