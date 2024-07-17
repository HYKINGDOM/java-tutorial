package com.java.tutorial.project.service;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.core.util.ClassUtil;
import com.mybatisflex.core.util.SqlUtil;

import java.util.Collection;

/**
 * 自定义的服务基类接口
 *
 * @author dataprince数据小王子
 */
public interface IBaseService<T> extends IService<T> {

    /**
     * <p>带主键保存实体类对象数据，适用于中间表有联合主键场合但是不通过主键生成器生成主键值，而是程序自己提供主键值。
     *
     * @param entity 实体类对象
     * @return 受影响的行数
     * @apiNote 默认调用的是 {@link BaseMapper#insertSelectiveWithPk(Object)} 方法，忽略实体类 {@code null} 属性的数据，使数据库配置的默认值生效。
     */
    default int saveWithPk(T entity) {
        return getMapper().insertSelectiveWithPk(entity);
    }

    /**
     * <p>带主键批量保存实体类对象数据，适用于中间表有联合主键场合但是不通过主键生成器生成主键值，而是程序自己提供主键值，例如sys_role_menu。
     *
     * @param entities 实体类对象
     * @return {@code true} 保存成功，{@code false} 保存失败。
     * @apiNote 默认调用的是 {@link BaseMapper#insertSelectiveWithPk(Object)} 方法，忽略实体类 {@code null} 属性的数据，使数据库配置的默认值生效。
     */
    default boolean saveBatchWithPk(Collection<T> entities) {
        return saveBatchWithPk(entities, DEFAULT_BATCH_SIZE);
    }

    /**
     * <p>带主键批量保存实体类对象数据，适用于中间表有联合主键场合但是不通过主键生成器生成主键值，而是程序自己提供主键值，例如sys_role_menu。
     *
     * @param entities  实体类对象
     * @param batchSize 每次保存切分的数量
     * @return {@code true} 保存成功，{@code false} 保存失败。
     * @apiNote 默认调用的是 {@link BaseMapper#insertSelectiveWithPk(Object)} 方法，忽略实体类 {@code null} 属性的数据，使数据库配置的默认值生效。
     */
    default boolean saveBatchWithPk(Collection<T> entities, int batchSize) {
        Class<BaseMapper<T>> usefulClass = (Class<BaseMapper<T>>)ClassUtil.getUsefulClass(getMapper().getClass());
        return SqlUtil.toBool(Db.executeBatch(entities, batchSize, usefulClass, BaseMapper::insertSelectiveWithPk));
    }
}
