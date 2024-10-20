package com.java.tutorial.project.infrastructure.mapper;

import com.java.tutorial.project.domain.base.SysUserInfo;
import com.java.tutorial.project.infrastructure.entity.SysUserInfoEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author meta
 */
@Mapper(componentModel = "spring")
public interface SysUserInfoMapping {

    SysUserInfo toSysUserInfo(SysUserInfoEntity sysUserInfoEntity);

    SysUserInfoEntity toSysUserInfoEntity(SysUserInfo sysUserInfo);

    /**
     * 转换
     *
     * @param sysUserInfoEntities
     * @return
     */
    List<SysUserInfo> toSysUserInfos(List<SysUserInfoEntity> sysUserInfoEntities);
}
