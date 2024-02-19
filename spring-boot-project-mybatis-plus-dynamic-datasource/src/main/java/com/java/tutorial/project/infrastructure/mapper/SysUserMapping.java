package com.java.tutorial.project.infrastructure.mapper;

import com.java.tutorial.project.domain.base.SysUser;
import com.java.tutorial.project.infrastructure.entity.SysUserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysUserMapping {

    SysUser toSysUser(SysUserEntity sysUserEntity);

    SysUserEntity toSysUserEntity(SysUser sysUser);

    List<SysUser> toSysUsers(List<SysUserEntity> sysUserEntities);

    List<SysUserEntity> toSysUserEntities(List<SysUser> sysUsers);
}
