package com.java.tutorial.project.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.java.tutorial.project.domain.base.SysUser;
import com.java.tutorial.project.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hy
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Integer id) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    @DSTransactional
    public SysUser insert(SysUser sysUser) {
        return null;
    }

    /**
     * 更新数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
