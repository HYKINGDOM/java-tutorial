package com.java.tutorial.project.infrastructure.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.java.tutorial.project.domain.base.SysUserInfo;
import com.java.tutorial.project.infrastructure.SysUserInfoRepository;
import com.java.tutorial.project.infrastructure.dao.SysUserInfoDao;
import com.java.tutorial.project.infrastructure.entity.SysUserInfoEntity;
import com.java.tutorial.project.infrastructure.mapper.SysUserInfoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author meta
 */
@Repository
public class SysUserInfoRepositoryImpl implements SysUserInfoRepository {

    @Autowired
    private SysUserInfoDao sysUserInfoDao;

    @Autowired
    private SysUserInfoMapping sysUserInfoMapping;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserInfo queryById(Integer id) {
        SysUserInfoEntity sysUserInfoEntity = sysUserInfoDao.selectById(id);
        return sysUserInfoMapping.toSysUserInfo(sysUserInfoEntity);
    }

    /**
     * 新增数据
     *
     * @param sysUserInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserInfo insert(SysUserInfo sysUserInfo) {
        SysUserInfoEntity sysUserInfoEntity = sysUserInfoMapping.toSysUserInfoEntity(sysUserInfo);
        sysUserInfoDao.insert(sysUserInfoEntity);
        return sysUserInfoMapping.toSysUserInfo(sysUserInfoEntity);
    }

    /**
     * 更新数据
     *
     * @param sysUserInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserInfo update(SysUserInfo sysUserInfo) {
        SysUserInfoEntity sysUserInfoEntity = sysUserInfoMapping.toSysUserInfoEntity(sysUserInfo);
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<SysUserInfoEntity> chainWrapper =
            new LambdaUpdateChainWrapper<SysUserInfoEntity>(sysUserInfoDao);
        if (StrUtil.isNotBlank(sysUserInfoEntity.getNikeName())) {
            chainWrapper.eq(SysUserInfoEntity::getNikeName, sysUserInfoEntity.getNikeName());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getUserSignature())) {
            chainWrapper.eq(SysUserInfoEntity::getUserSignature, sysUserInfoEntity.getUserSignature());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getIdCardNo())) {
            chainWrapper.eq(SysUserInfoEntity::getIdCardNo, sysUserInfoEntity.getIdCardNo());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getAvatar())) {
            chainWrapper.eq(SysUserInfoEntity::getAvatar, sysUserInfoEntity.getAvatar());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getWeight())) {
            chainWrapper.eq(SysUserInfoEntity::getWeight, sysUserInfoEntity.getWeight());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getHeight())) {
            chainWrapper.eq(SysUserInfoEntity::getHeight, sysUserInfoEntity.getHeight());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getNation())) {
            chainWrapper.eq(SysUserInfoEntity::getNation, sysUserInfoEntity.getNation());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getPolitical())) {
            chainWrapper.eq(SysUserInfoEntity::getPolitical, sysUserInfoEntity.getPolitical());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getMarital())) {
            chainWrapper.eq(SysUserInfoEntity::getMarital, sysUserInfoEntity.getMarital());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getDomicilePlaceProvince())) {
            chainWrapper.eq(SysUserInfoEntity::getDomicilePlaceProvince, sysUserInfoEntity.getDomicilePlaceProvince());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getDomicilePlaceCity())) {
            chainWrapper.eq(SysUserInfoEntity::getDomicilePlaceCity, sysUserInfoEntity.getDomicilePlaceCity());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getDomicilePlaceAddress())) {
            chainWrapper.eq(SysUserInfoEntity::getDomicilePlaceAddress, sysUserInfoEntity.getDomicilePlaceAddress());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getRegisterIp())) {
            chainWrapper.eq(SysUserInfoEntity::getRegisterIp, sysUserInfoEntity.getRegisterIp());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getLastLoginIp())) {
            chainWrapper.eq(SysUserInfoEntity::getLastLoginIp, sysUserInfoEntity.getLastLoginIp());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getCreatedBy())) {
            chainWrapper.eq(SysUserInfoEntity::getCreatedBy, sysUserInfoEntity.getCreatedBy());
        }
        if (StrUtil.isNotBlank(sysUserInfoEntity.getUpdatedBy())) {
            chainWrapper.eq(SysUserInfoEntity::getUpdatedBy, sysUserInfoEntity.getUpdatedBy());
        }
        //2. 设置主键，并更新
        chainWrapper.set(SysUserInfoEntity::getId, sysUserInfoEntity.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if (ret) {
            return queryById(sysUserInfoEntity.getId());
        } else {
            return sysUserInfo;
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
        int total = sysUserInfoDao.deleteById(id);
        return total > 0;
    }
}
