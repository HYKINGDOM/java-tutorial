package com.java.tutorial.project.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.java.tutorial.project.domain.GenTable;
import com.java.tutorial.project.domain.GenTableColumn;
import com.java.tutorial.project.mapper.GenTableColumnMapper;
import com.java.tutorial.project.mapper.GenTableMapper;
import com.java.tutorial.project.constant.Constants;
import com.java.tutorial.project.service.IGenTableService;
import com.java.tutorial.project.util.DateUtils;
import com.java.tutorial.project.util.FileUtils;
import com.java.tutorial.project.constant.GenConstants;
import com.java.tutorial.project.util.GenUtils;
import com.java.tutorial.project.util.PageQuery;
import com.java.tutorial.project.util.StreamUtils;
import com.java.tutorial.project.util.TableDataInfo;
import com.java.tutorial.project.util.VelocityInitializer;
import com.java.tutorial.project.util.VelocityUtils;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryMethods;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.java.tutorial.project.domain.table.GenTableColumnTableDef.GEN_TABLE_COLUMN;
import static com.java.tutorial.project.domain.table.GenTableTableDef.GEN_TABLE;

/**
 * 业务 服务层实现
 *
 * @author meta
 */
@Slf4j
@Service
public class GenTableServiceImpl extends BaseServiceImpl<GenTableMapper, GenTable> implements IGenTableService {
    @Resource
    private GenTableMapper genTableMapper;
    @Resource
    private GenTableColumnMapper genTableColumnMapper;

    @Override
    public QueryWrapper query() {
        return super.query().from(GEN_TABLE);
    }

    private QueryWrapper buildQueryWrapper(GenTable genTable) {
        QueryWrapper queryWrapper = super.buildBaseQueryWrapper();
        if (StringUtils.isNotBlank(genTable.getTableName())) {
            queryWrapper.and(QueryMethods.lower(GEN_TABLE.TABLE_NAME).like(genTable.getTableName().toLowerCase()));
        }
        if (StringUtils.isNotBlank(genTable.getTableComment())) {
            queryWrapper.and(
                QueryMethods.lower(GEN_TABLE.TABLE_COMMENT).like(genTable.getTableComment().toLowerCase()));
        }
        Map<String, Object> params = genTable.getParams();
        if (params.get("beginTime") != null && params.get("endTime") != null) {
            queryWrapper.and(GEN_TABLE.CREATE_TIME.between(params.get("beginTime"), params.get("endTime")));
        }
        queryWrapper.orderBy(GEN_TABLE.TABLE_ID.desc());
        return queryWrapper;

    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public GenTable selectGenTableById(Long id) {
        /*SELECT t.table_id, t.table_name, t.table_comment, t.sub_table_name, t.sub_table_fk_name, t.class_name, t.tpl_category, t.package_name, t.module_name, t.business_name, t.function_name, t.function_author, t.gen_type, t.gen_path, t.options, t.remark,
        c.column_id, c.column_name, c.column_comment, c.column_type, c.java_type, c.java_field, c.is_pk, c.is_increment, c.is_required, c.is_insert, c.is_edit, c.is_list, c.is_query, c.query_type, c.html_type, c.dict_type, c.sort
        FROM gen_table t
        LEFT JOIN gen_table_column c ON t.table_id = c.table_id
        where t.table_id = #{tableId} order by c.sort*/

        GenTable genTable = genTableMapper.selectOneWithRelationsById(id);
        setTableFromOptions(genTable);
        return genTable;
    }

    /**
     * 分页查询业务列表
     *
     * @param genTable 业务信息
     * @return 分页集合
     */
    @Override
    public TableDataInfo<GenTable> selectPage(GenTable genTable) {
        QueryWrapper queryWrapper = buildQueryWrapper(genTable);
        Page<GenTable> page = this.pageAs(PageQuery.build(), queryWrapper, GenTable.class);
        return TableDataInfo.build(page);
    }

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    @Override
    public List<GenTable> selectDbTableList(GenTable genTable) {
        return genTableMapper.selectDbTableList(genTable);
    }

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    @Override
    public List<GenTable> selectDbTableListByNames(String[] tableNames) {
        return genTableMapper.selectDbTableListByNames(tableNames);
    }

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    @Override
    public List<GenTable> selectGenTableAll() {
        QueryWrapper queryWrapper =
            query().leftJoin(GEN_TABLE_COLUMN).on(GEN_TABLE_COLUMN.TABLE_ID.eq(GEN_TABLE.TABLE_ID))
                .orderBy(GEN_TABLE_COLUMN.SORT.asc());
        return this.list(queryWrapper);
    }

    /**
     * 修改业务
     *
     * @param genTable 业务信息 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGenTable(GenTable genTable) {
        String options = JSON.toJSONString(genTable.getParams());
        genTable.setOptions(options);

        Long loginUserId = 1L;
        Date createTime = new Date();
        genTable.setUpdateBy(loginUserId);
        genTable.setUpdateTime(createTime);

        boolean updated = this.updateById(genTable);
        if (updated) {
            for (GenTableColumn genTableColumn : genTable.getColumns()) {
                genTableColumn.setUpdateBy(loginUserId);
                genTableColumn.setUpdateTime(createTime);
                genTableColumnMapper.update(genTableColumn);
            }
        }
    }

    /**
     * 删除业务对象
     *
     * @param tableIds 需要删除的数据ID 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGenTableByIds(Long[] tableIds) {
        genTableMapper.deleteBatchByIds(Arrays.asList(tableIds));
        genTableColumnMapper.deleteGenTableColumnByIds(tableIds);
    }

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    @Override
    @Transactional
    public void importGenTable(List<GenTable> tableList) {
        try {
            for (GenTable table : tableList) {
                String tableName = table.getTableName();
                GenUtils.initTable(table);

                Long loginUserId = 1L;
                Date createTime = new Date();
                table.setCreateBy(loginUserId);
                table.setCreateTime(createTime);
                table.setUpdateBy(loginUserId);
                table.setUpdateTime(createTime);

                boolean saved = this.save(table);
                if (saved) {
                    // 保存列信息
                    List<GenTableColumn> genTableColumns = genTableColumnMapper.selectDbTableColumnsByName(tableName);
                    List<GenTableColumn> saveColumns = new ArrayList<>();
                    for (GenTableColumn column : genTableColumns) {
                        GenUtils.initColumnField(column, table);

                        column.setCreateBy(loginUserId);
                        column.setCreateTime(createTime);
                        column.setUpdateBy(loginUserId);
                        column.setUpdateTime(createTime);

                        saveColumns.add(column);
                    }
                    if (CollUtil.isNotEmpty(saveColumns)) {
                        genTableColumnMapper.insertBatch(saveColumns);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }

    /**
     * 预览代码
     *
     * @param tableId   表编号
     * @param frontType 前端界面类型取值：0是element-js、1是element-ts、2是antdesign-ts
     * @return 预览数据列表
     */
    @Override
    public Map<String, String> previewCode(Long tableId, Integer frontType) {
        Map<String, String> dataMap = new LinkedHashMap<>();
        // 查询表信息
        GenTable table = selectGenTableById(tableId);

        //设置生成的sys_menu7条记录的主键值
        setMenuIds(table);
        // 设置主子表信息
        setSubTable(table);
        // 设置主键列信息
        setPkColumn(table);
        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory(), frontType);
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return dataMap;
    }

    /**
     * 生成代码（下载方式）
     *
     * @param tableId   表id
     * @param frontType 前端界面类型取值：0是element-js、1是element-ts、2是antdesign-ts
     * @return 数据
     */
    @Override
    public byte[] downloadCode(Long tableId, Integer frontType) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(tableId, zip, frontType);
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    /**
     * 生成代码（自定义路径）
     *
     * @param tableId 表ID
     */
    @Override
    public void generatorCode(Long tableId, Integer frontType) {
        // 查询表信息
        //GenTable table = genTableMapper.selectGenTableByName(tableName);
        GenTable table = genTableMapper.selectOneWithRelationsById(tableId);
        // 设置主子表信息
        setSubTable(table);
        //设置生成的sys_menu7条记录的主键值
        setMenuIds(table);
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory(), frontType);
        for (String template : templates) {
            if (!StringUtils.containsAny(template, "sql.vm", "element.js.api.vm", "element.js.index.vue.vm",
                "element.js.index-tree.vue.vm")) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, Constants.UTF8);
                tpl.merge(context, sw);
                try {
                    String path = getGenPath(table, template);
                    FileUtils.writeUtf8String(sw.toString(), path);
                } catch (Exception e) {
                    throw new RuntimeException("渲染模板失败，表名：" + table.getTableName());
                }
            }
        }
    }

    /**
     * 同步数据库
     *
     * @param tableId 表ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void synchDb(Long tableId) {
        Long loginUserId = 1L;
        Date createTime = new Date();

        GenTable table = genTableMapper.selectOneWithRelationsById(tableId);
        List<GenTableColumn> tableColumns = table.getColumns();
        Map<String, GenTableColumn> tableColumnMap =
            StreamUtils.toIdentityMap(tableColumns, GenTableColumn::getColumnName);

        List<GenTableColumn> dbTableColumns = genTableColumnMapper.selectDbTableColumnsByName(table.getTableName());
        if (CollUtil.isEmpty(dbTableColumns)) {
            throw new RuntimeException("同步数据失败，原表结构不存在");
        }
        List<String> dbTableColumnNames = StreamUtils.toList(dbTableColumns, GenTableColumn::getColumnName);

        List<GenTableColumn> saveColumns = new ArrayList<>();
        dbTableColumns.forEach(column -> {
            GenUtils.initColumnField(column, table);
            if (tableColumnMap.containsKey(column.getColumnName())) {
                GenTableColumn prevColumn = tableColumnMap.get(column.getColumnName());
                column.setColumnId(prevColumn.getColumnId());
                if (column.isList()) {
                    // 如果是列表，继续保留查询方式/字典类型选项
                    column.setDictType(prevColumn.getDictType());
                    column.setQueryType(prevColumn.getQueryType());
                }
                if (StringUtils.isNotEmpty(
                    prevColumn.getIsRequired()) && !column.isPk() && (column.isInsert() || column.isEdit()) && ((column.isUsableColumn()) || (!column.isSuperColumn()))) {
                    // 如果是(新增/修改&非主键/非忽略及父属性)，继续保留必填/显示类型选项
                    column.setIsRequired(prevColumn.getIsRequired());
                    column.setHtmlType(prevColumn.getHtmlType());
                }
                column.setVersion(prevColumn.getVersion());
                column.setUpdateBy(loginUserId);
                column.setUpdateTime(createTime);
            } else {
                column.setVersion(0);
                column.setCreateBy(loginUserId);
                column.setCreateTime(createTime);
                column.setUpdateBy(loginUserId);
                column.setUpdateTime(createTime);
            }
            saveColumns.add(column);
        });
        if (CollUtil.isNotEmpty(saveColumns)) {
            Db.executeBatch(saveColumns, 500, GenTableColumnMapper.class, BaseMapper::insertOrUpdate);
        }

        List<GenTableColumn> delColumns =
            StreamUtils.filter(tableColumns, column -> !dbTableColumnNames.contains(column.getColumnName()));
        if (CollUtil.isNotEmpty(delColumns)) {
            List<Long> ids = StreamUtils.toList(delColumns, GenTableColumn::getColumnId);
            if (CollUtil.isNotEmpty(ids)) {
                genTableColumnMapper.deleteBatchByIds(ids);
            }
        }
    }

    /**
     * 批量生成代码（下载方式）
     *
     * @param tableIds  表ids
     * @param frontType 前端界面类型取值：0是element-js、1是element-ts、2是antdesign-ts
     * @return 数据
     */
    @Override
    public byte[] downloadCode(String[] tableIds, Integer frontType) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableId : tableIds) {
            generatorCode(Long.parseLong(tableId), zip, frontType);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     */
    private void generatorCode(Long tableId, ZipOutputStream zip, Integer frontType) {
        // 查询表信息
        GenTable table = genTableMapper.selectOneWithRelationsById(tableId);
        // 设置主子表信息
        setSubTable(table);
        //设置生成的sys_menu7条记录的主键值
        setMenuIds(table);
        // 设置主键列信息
        setPkColumn(table);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory(), frontType);
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IoUtil.write(zip, StandardCharsets.UTF_8, false, sw.toString());
                IoUtil.close(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    @Override
    public void validateEdit(GenTable genTable) {
        if (GenConstants.TPL_TREE.equals(genTable.getTplCategory())) {
            String options = JSON.toJSONString(genTable.getParams());
            JSONObject paramsObj = JSON.parseObject(options);
            if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_CODE))) {
                throw new RuntimeException("树编码字段不能为空");
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_PARENT_CODE))) {
                throw new RuntimeException("树父编码字段不能为空");
            } else if (StringUtils.isEmpty(paramsObj.getString(GenConstants.TREE_NAME))) {
                throw new RuntimeException("树名称字段不能为空");
            } else if (GenConstants.TPL_SUB.equals(genTable.getTplCategory())) {
                if (StringUtils.isEmpty(genTable.getSubTableName())) {
                    throw new RuntimeException("关联子表的表名不能为空");
                } else if (StringUtils.isEmpty(genTable.getSubTableFkName())) {
                    throw new RuntimeException("子表关联的外键名不能为空");
                }
            }
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(GenTable table) {
        for (GenTableColumn column : table.getColumns()) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (ObjectUtil.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }
        if (GenConstants.TPL_SUB.equals(table.getTplCategory())) {
            for (GenTableColumn column : table.getSubTable().getColumns()) {
                if (column.isPk()) {
                    table.getSubTable().setPkColumn(column);
                    break;
                }
            }
            if (ObjectUtil.isNull(table.getSubTable().getPkColumn())) {
                table.getSubTable().setPkColumn(table.getSubTable().getColumns().get(0));
            }
        }
    }

    /**
     * 设置主子表信息
     *
     * @param table 业务表信息
     */
    public void setSubTable(GenTable table) {
        String subTableName = table.getSubTableName();
        if (StringUtils.isNotEmpty(subTableName)) {
            table.setSubTable(genTableMapper.selectGenTableByName(subTableName));
        }
    }

    /**
     * 设置代码生成其他选项值
     *
     * @param genTable 设置后的生成对象
     */
    public void setTableFromOptions(GenTable genTable) {
        JSONObject paramsObj = JSON.parseObject(genTable.getOptions());
        if (ObjectUtil.isNotNull(paramsObj)) {
            String treeCode = paramsObj.getString(GenConstants.TREE_CODE);
            String treeParentCode = paramsObj.getString(GenConstants.TREE_PARENT_CODE);
            String treeName = paramsObj.getString(GenConstants.TREE_NAME);
            String parentMenuId = paramsObj.getString(GenConstants.PARENT_MENU_ID);
            String parentMenuName = paramsObj.getString(GenConstants.PARENT_MENU_NAME);

            genTable.setTreeCode(treeCode);
            genTable.setTreeParentCode(treeParentCode);
            genTable.setTreeName(treeName);
            genTable.setParentMenuId(parentMenuId);
            genTable.setParentMenuName(parentMenuName);
        }
    }

    /**
     * 获取代码生成地址
     *
     * @param table    业务表信息
     * @param template 模板文件路径
     * @return 生成地址
     */
    public static String getGenPath(GenTable table, String template) {
        String genPath = table.getGenPath();
        if (StringUtils.equals(genPath, "/")) {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + VelocityUtils.getFileName(
                template, table);
        }
        return genPath + File.separator + VelocityUtils.getFileName(template, table);
    }

    /**
     * 设置生成的sys_menu 7条记录的主键值 日期时间格式YYYYMMDDHHMMSS转化为数值，然后+01
     *
     * @param table table
     */
    private void setMenuIds(GenTable table) {
        String nowStr = DateUtils.dateTimeNow();
        List<Long> menuIds = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            menuIds.add(Long.valueOf(nowStr + "0" + i));
        }
        table.setMenuIds(menuIds);
    }
}
