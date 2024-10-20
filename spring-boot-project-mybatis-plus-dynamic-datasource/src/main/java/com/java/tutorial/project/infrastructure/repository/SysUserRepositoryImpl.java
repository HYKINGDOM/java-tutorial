package com.java.tutorial.project.infrastructure.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.java.tutorial.project.domain.base.SysUser;
import com.java.tutorial.project.infrastructure.SysUserRepository;
import com.java.tutorial.project.infrastructure.dao.SysUserDao;
import com.java.tutorial.project.infrastructure.entity.SysUserEntity;
import com.java.tutorial.project.infrastructure.mapper.SysUserMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author meta
 */
@Slf4j
@Repository
public class SysUserRepositoryImpl implements SysUserRepository {

    @Autowired
    private SysUserMapping sysUserMapping;

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Integer id) {
        SysUserEntity sysUserEntity = sysUserDao.selectById(id);
        return sysUserMapping.toSysUser(sysUserEntity);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        SysUserEntity sysUserEntity = sysUserMapping.toSysUserEntity(sysUser);
        sysUserDao.insert(sysUserEntity);
        return sysUserMapping.toSysUser(sysUserEntity);
    }

    /**
     * 更新数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        SysUserEntity sysUserEntity = sysUserMapping.toSysUserEntity(sysUser);

        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<SysUserEntity> chainWrapper = new LambdaUpdateChainWrapper<>(sysUserDao);
        if (StrUtil.isNotBlank(sysUserEntity.getUserName())) {
            chainWrapper.eq(SysUserEntity::getUserName, sysUserEntity.getUserName());
        }
        if (StrUtil.isNotBlank(sysUserEntity.getPassword())) {
            chainWrapper.eq(SysUserEntity::getPassword, sysUserEntity.getPassword());
        }
        if (StrUtil.isNotBlank(sysUserEntity.getEmail())) {
            chainWrapper.eq(SysUserEntity::getEmail, sysUserEntity.getEmail());
        }
        if (StrUtil.isNotBlank(sysUserEntity.getMobilePhone())) {
            chainWrapper.eq(SysUserEntity::getMobilePhone, sysUserEntity.getMobilePhone());
        }
        if (StrUtil.isNotBlank(sysUserEntity.getCreatedBy())) {
            chainWrapper.eq(SysUserEntity::getCreatedBy, sysUserEntity.getCreatedBy());
        }
        if (StrUtil.isNotBlank(sysUserEntity.getUpdatedBy())) {
            chainWrapper.eq(SysUserEntity::getUpdatedBy, sysUserEntity.getUpdatedBy());
        }
        //2. 设置主键，并更新
        chainWrapper.set(SysUserEntity::getId, sysUserEntity.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if (ret) {
            return queryById(sysUserEntity.getId());
        } else {
            return sysUser;
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        int total = sysUserDao.deleteById(id);
        return total > 0;
    }
}
