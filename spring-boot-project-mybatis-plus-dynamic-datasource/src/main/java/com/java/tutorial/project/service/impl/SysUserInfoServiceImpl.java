package com.java.tutorial.project.service.impl;

import com.java.tutorial.project.domain.base.SysUserInfo;
import com.java.tutorial.project.service.SysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hy
 */
@Slf4j
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserInfo queryById(Integer id) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param sysUserInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserInfo insert(SysUserInfo sysUserInfo) {
        return null;
    }

    /**
     * 更新数据
     *
     * @param sysUserInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserInfo update(SysUserInfo sysUserInfo) {
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
