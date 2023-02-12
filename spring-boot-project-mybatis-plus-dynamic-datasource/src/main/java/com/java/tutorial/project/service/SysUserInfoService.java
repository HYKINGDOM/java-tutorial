package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.base.SysUserInfo;

public interface SysUserInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserInfo queryById(Integer id);
    /**
     * 新增数据
     *
     * @param sysUserInfo 实例对象
     * @return 实例对象
     */
    SysUserInfo insert(SysUserInfo sysUserInfo);
    /**
     * 更新数据
     *
     * @param sysUserInfo 实例对象
     * @return 实例对象
     */
    SysUserInfo update(SysUserInfo sysUserInfo);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}
