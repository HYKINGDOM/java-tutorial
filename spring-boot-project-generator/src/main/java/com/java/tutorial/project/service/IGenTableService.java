package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.GenTable;
import com.java.tutorial.project.util.TableDataInfo;

import java.util.List;
import java.util.Map;

/**
 * 业务 服务层
 *
 * @author meta
 * @author meta
 */
public interface IGenTableService extends IBaseService<GenTable> {
    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 分页查询业务列表
     *
     * @param genTable 业务信息
     * @return 分页集合
     */
    TableDataInfo<GenTable> selectPage(GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    List<GenTable> selectGenTableAll();

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(Long id);

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    void updateGenTable(GenTable genTable);

    /**
     * 删除业务信息
     *
     * @param tableIds 需要删除的表数据ID
     * @return 结果
     */
    void deleteGenTableByIds(Long[] tableIds);

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    void importGenTable(List<GenTable> tableList);

    /**
     * 预览代码
     *
     * @param tableId   表编号
     * @param frontType 前端界面类型取值：0是element-js、1是element-ts、2是antdesign-ts
     * @return 预览数据列表
     */
    Map<String, String> previewCode(Long tableId, Integer frontType);

    /**
     * 生成代码（自定义路径）
     *
     * @param tableId   表ID
     * @param frontType 前端界面类型取值：0是element-js、1是element-ts、2是antdesign-ts
     * @return 数据
     */
    void generatorCode(Long tableId, Integer frontType);

    /**
     * 同步数据库
     *
     * @param tableId 表ID
     */
    void synchDb(Long tableId);

    /**
     * 生成代码（下载方式）
     *
     * @param tableId   表名称
     * @param frontType 前端界面类型取值：0是element-js、1是element-ts、2是antdesign-ts
     * @return 数据
     */
    byte[] downloadCode(Long tableId, Integer frontType);

    /**
     * 批量生成代码（下载方式）
     *
     * @param tableIds  表ID数组
     * @param frontType 前端界面类型取值：0是element-js、1是element-ts、2是antdesign-ts
     * @return 数据
     */
    byte[] downloadCode(String[] tableIds, Integer frontType);

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    void validateEdit(GenTable genTable);
}
